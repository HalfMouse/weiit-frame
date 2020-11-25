package com.weiit.web.admin.system.service;

import com.weiit.core.entity.FormMap;
import com.weiit.core.service.BaseService;

/**
 * 常用功能菜单接口
 * @author ~~迷途~~
 * @date：2017-7-7 上午11:31:37
 * @version 1.0
 * @company http://www.wei-it.com
 */
public interface FrequentResourceService extends BaseService {

	void saveFrequentResourceTrans(FormMap formMap);
	
}
