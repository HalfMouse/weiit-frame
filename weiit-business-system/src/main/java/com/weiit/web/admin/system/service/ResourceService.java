package com.weiit.web.admin.system.service;


import java.util.List;

import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;
import com.weiit.core.service.BaseService;



/**
 * 资源管理业务接口
 * @author 半个鼠标
 * @date：2017年2月14日 上午2:37:34
 * @version 1.0
 * @company http://www.wei-it.com
 */
public interface ResourceService extends BaseService{
	
	public void updateState(FormMap formMap);

	public List<E> getRoleResources(FormMap formMap);
	
	public  List<E> getResourcesList(FormMap formMap);
	
	public  List<E> getResourcesAndButtonList(FormMap formMap);
}
