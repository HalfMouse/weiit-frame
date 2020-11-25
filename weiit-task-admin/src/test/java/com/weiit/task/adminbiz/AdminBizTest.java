package com.weiit.task.adminbiz;

import com.weiit.task.core.biz.AdminBiz;
import com.weiit.task.core.biz.model.RegistryParam;
import com.weiit.task.core.biz.model.ReturnT;
import com.weiit.task.core.enums.RegistryConfig;
import com.weiit.task.core.rpc.netcom.NetComClientProxy;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author：半个鼠标
 * @date：2018年4月29日
 * @version 1.0
 * @company http://www.wei-it.com 微邦互联
 */
public class AdminBizTest {

    // admin-client
    private static String addressUrl = "http://127.0.0.1:8080/weiit-task-admin".concat(AdminBiz.MAPPING);
    private static String accessToken = null;

    /**
     * registry executor
     *
     * @throws Exception
     */
    @Test
    public void registryTest() throws Exception {
        AdminBiz adminBiz = (AdminBiz) new NetComClientProxy(AdminBiz.class, addressUrl, accessToken).getObject();

        // test executor registry
        RegistryParam registryParam = new RegistryParam(RegistryConfig.RegistType.EXECUTOR.name(), "weiit-task-executor-example", "127.0.0.1:9998");
        ReturnT<String> returnT = adminBiz.registry(registryParam);
        Assert.assertTrue(returnT.getCode() == ReturnT.SUCCESS_CODE);
    }

    /**
     * registry executor remove
     *
     * @throws Exception
     */
    @Test
    public void registryRemove() throws Exception {
        AdminBiz adminBiz = (AdminBiz) new NetComClientProxy(AdminBiz.class, addressUrl, accessToken).getObject();

        // test executor registry remove
        RegistryParam registryParam = new RegistryParam(RegistryConfig.RegistType.EXECUTOR.name(), "weiit-task-executor-example", "127.0.0.1:9998");
        ReturnT<String> returnT = adminBiz.registryRemove(registryParam);
        Assert.assertTrue(returnT.getCode() == ReturnT.SUCCESS_CODE);
    }

    /**
     * trigger job for once
     *
     * @throws Exception
     */
    @Test
    public void triggerJob() throws Exception {
        AdminBiz adminBiz = (AdminBiz) new NetComClientProxy(AdminBiz.class, addressUrl, accessToken).getObject();

        int jobId = 1;
        ReturnT<String> returnT = adminBiz.triggerJob(jobId);
        Assert.assertTrue(returnT.getCode() == ReturnT.SUCCESS_CODE);
    }

}
