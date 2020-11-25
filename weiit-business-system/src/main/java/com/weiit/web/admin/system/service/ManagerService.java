package com.weiit.web.admin.system.service;

import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;
import com.weiit.core.service.BaseService;





/**
 * 管理员业务接口
 * @author 半个鼠标
 * @date：2017年2月14日 上午2:37:06
 * @version 1.0
 * @company http://www.wei-it.com
 */
public interface ManagerService extends BaseService{
	
	E login(FormMap formMap);
	
	void editManagerPassword(FormMap formMap);
	
	void createTrans(FormMap param);
	
	void updateTrans(FormMap param);
	
	void deleteTrans(FormMap param);
	
	
}
