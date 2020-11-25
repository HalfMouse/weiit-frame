package com.weiit.task.core.executor;

import com.weiit.task.core.biz.AdminBiz;
import com.weiit.task.core.biz.ExecutorBiz;
import com.weiit.task.core.biz.impl.ExecutorBizImpl;
import com.weiit.task.core.handler.Task;
import com.weiit.task.core.handler.annotation.TaskHandler;
import com.weiit.task.core.log.TaskFileAppender;
import com.weiit.task.core.rpc.netcom.NetComClientProxy;
import com.weiit.task.core.rpc.netcom.NetComServerFactory;
import com.weiit.task.core.thread.TaskLogFileCleanThread;
import com.weiit.task.core.thread.TaskThread;
import com.weiit.task.core.util.NetUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xuxueli on 2016/3/2 21:14.
 */
public class TaskExecutor implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(TaskExecutor.class);

    // ---------------------- param ----------------------
    private String adminAddresses;
    private String appName;
    private String ip;
    private int port;
    private String accessToken;
    private String logPath;
    private int logRetentionDays;

    public void setAdminAddresses(String adminAddresses) {
        this.adminAddresses = adminAddresses;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }
    public void setLogRetentionDays(int logRetentionDays) {
        this.logRetentionDays = logRetentionDays;
    }

    // ---------------------- applicationContext ----------------------
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    // ---------------------- start + stop ----------------------
    public void start() throws Exception {
        // init admin-client
        initAdminBizList(adminAddresses, accessToken);

        // init executor-jobHandlerRepository
        initJobHandlerRepository(applicationContext);

        // init logpath
        TaskFileAppender.initLogPath(logPath);

        // init executor-server
        initExecutorServer(port, ip, appName, accessToken);

        // init JobLogFileCleanThread
        TaskLogFileCleanThread.getInstance().start(logRetentionDays);
    }
    public void destroy(){
        // destory JobThreadRepository
        if (JobThreadRepository.size() > 0) {
            for (Map.Entry<Integer, TaskThread> item: JobThreadRepository.entrySet()) {
                removeJobThread(item.getKey(), "Web容器销毁终止");
            }
            JobThreadRepository.clear();
        }

        // destory executor-server
        stopExecutorServer();

        // destory JobLogFileCleanThread
        TaskLogFileCleanThread.getInstance().toStop();
    }


    // ---------------------- admin-client ----------------------
    private static List<AdminBiz> adminBizList;
    private static void initAdminBizList(String adminAddresses, String accessToken) throws Exception {
        if (adminAddresses!=null && adminAddresses.trim().length()>0) {
            for (String address: adminAddresses.trim().split(",")) {
                if (address!=null && address.trim().length()>0) {
                    String addressUrl = address.concat(AdminBiz.MAPPING);
                    AdminBiz adminBiz = (AdminBiz) new NetComClientProxy(AdminBiz.class, addressUrl, accessToken).getObject();
                    if (adminBizList == null) {
                        adminBizList = new ArrayList<AdminBiz>();
                    }
                    adminBizList.add(adminBiz);
                }
            }
        }
    }
    public static List<AdminBiz> getAdminBizList(){
        return adminBizList;
    }


    // ---------------------- executor-server(jetty) ----------------------
    private NetComServerFactory serverFactory = new NetComServerFactory();
    private void initExecutorServer(int port, String ip, String appName, String accessToken) throws Exception {
        // valid param
        port = port>0?port: NetUtil.findAvailablePort(9999);

        // start server
        NetComServerFactory.putService(ExecutorBiz.class, new ExecutorBizImpl());   // rpc-service, base on jetty
        NetComServerFactory.setAccessToken(accessToken);
        serverFactory.start(port, ip, appName); // jetty + registry
    }
    private void stopExecutorServer() {
        serverFactory.destroy();    // jetty + registry + callback
    }


    // ---------------------- job handler repository ----------------------
    private static ConcurrentHashMap<String, Task> jobHandlerRepository = new ConcurrentHashMap<String, Task>();
    public static Task registJobHandler(String name, Task jobHandler){
        logger.info(">>>>>>>>>>> xxl-job register jobhandler success, name:{}, jobHandler:{}", name, jobHandler);
        return jobHandlerRepository.put(name, jobHandler);
    }
    public static Task loadJobHandler(String name){
        return jobHandlerRepository.get(name);
    }
    private static void initJobHandlerRepository(ApplicationContext applicationContext){
        if (applicationContext == null) {
            return;
        }

        // init job handler action
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(TaskHandler.class);

        if (serviceBeanMap!=null && serviceBeanMap.size()>0) {
            for (Object serviceBean : serviceBeanMap.values()) {
                if (serviceBean instanceof Task){
                    String name = serviceBean.getClass().getAnnotation(TaskHandler.class).value();
                    Task handler = (Task) serviceBean;
                    if (loadJobHandler(name) != null) {
                        throw new RuntimeException("xxl-job jobhandler naming conflicts.");
                    }
                    registJobHandler(name, handler);
                }
            }
        }
    }


    // ---------------------- job thread repository ----------------------
    private static ConcurrentHashMap<Integer, TaskThread> JobThreadRepository = new ConcurrentHashMap<Integer, TaskThread>();
    public static TaskThread registJobThread(int jobId, Task handler, String removeOldReason){
        TaskThread newJobThread = new TaskThread(jobId, handler);
        newJobThread.start();
        logger.info(">>>>>>>>>>> xxl-job regist JobThread success, jobId:{}, handler:{}", new Object[]{jobId, handler});

        TaskThread oldJobThread = JobThreadRepository.put(jobId, newJobThread);	// putIfAbsent | oh my god, map's put method return the old value!!!
        if (oldJobThread != null) {
            oldJobThread.toStop(removeOldReason);
            oldJobThread.interrupt();
        }

        return newJobThread;
    }
    public static void removeJobThread(int jobId, String removeOldReason){
        TaskThread oldJobThread = JobThreadRepository.remove(jobId);
        if (oldJobThread != null) {
            oldJobThread.toStop(removeOldReason);
            oldJobThread.interrupt();
        }
    }
    public static TaskThread loadJobThread(int jobId){
        TaskThread jobThread = JobThreadRepository.get(jobId);
        return jobThread;
    }

}
