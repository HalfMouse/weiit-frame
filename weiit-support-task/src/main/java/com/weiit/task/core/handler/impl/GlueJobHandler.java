package com.weiit.task.core.handler.impl;

import com.weiit.task.core.biz.model.ReturnT;
import com.weiit.task.core.handler.Task;
import com.weiit.task.core.log.TaskLogger;

/**
 * glue job handler
 * @author xuxueli 2016-5-19 21:05:45
 */
public class GlueJobHandler extends Task {

	private long glueUpdatetime;
	private Task jobHandler;
	public GlueJobHandler(Task jobHandler, long glueUpdatetime) {
		this.jobHandler = jobHandler;
		this.glueUpdatetime = glueUpdatetime;
	}
	public long getGlueUpdatetime() {
		return glueUpdatetime;
	}

	@Override
	public ReturnT<String> execute(String param) throws Exception {
		TaskLogger.log("----------- glue.version:"+ glueUpdatetime +" -----------");
		return jobHandler.execute(param);
	}

}
