package com.weiit.task.core.handler.impl;

import com.weiit.task.core.biz.model.ReturnT;
import com.weiit.task.core.glue.GlueTypeEnum;
import com.weiit.task.core.handler.Task;
import com.weiit.task.core.log.TaskFileAppender;
import com.weiit.task.core.log.TaskLogger;
import com.weiit.task.core.util.ScriptUtil;
import com.weiit.task.core.util.ShardingUtil;

/**
 * Created by xuxueli on 17/4/27.
 */
public class ScriptJobHandler extends Task {

    private int jobId;
    private long glueUpdatetime;
    private String gluesource;
    private GlueTypeEnum glueType;

    public ScriptJobHandler(int jobId, long glueUpdatetime, String gluesource, GlueTypeEnum glueType){
        this.jobId = jobId;
        this.glueUpdatetime = glueUpdatetime;
        this.gluesource = gluesource;
        this.glueType = glueType;
    }

    public long getGlueUpdatetime() {
        return glueUpdatetime;
    }

    @Override
    public ReturnT<String> execute(String param) throws Exception {

        if (!glueType.isScript()) {
            return new ReturnT<String>(Task.FAIL.getCode(), "glueType["+ glueType +"] invalid.");
        }

        // cmd
        String cmd = glueType.getCmd();

        // make script file
        String scriptFileName = TaskFileAppender.getGlueSrcPath()
                .concat("/")
                .concat(String.valueOf(jobId))
                .concat("_")
                .concat(String.valueOf(glueUpdatetime))
                .concat(glueType.getSuffix());
        ScriptUtil.markScriptFile(scriptFileName, gluesource);

        // log file
        String logFileName = TaskFileAppender.contextHolder.get();

        // script params：0=param、1=分片序号、2=分片总数
        ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
        String[] scriptParams = new String[3];
        scriptParams[0] = param;
        scriptParams[1] = String.valueOf(shardingVO.getIndex());
        scriptParams[2] = String.valueOf(shardingVO.getTotal());

        // invoke
        TaskLogger.log("----------- script file:"+ scriptFileName +" -----------");
        int exitValue = ScriptUtil.execToFile(cmd, scriptFileName, logFileName, scriptParams);
        ReturnT<String> result = (exitValue==0)?Task.SUCCESS:new ReturnT<String>(Task.FAIL.getCode(), "script exit value("+exitValue+") is failed");
        return result;
    }

}
