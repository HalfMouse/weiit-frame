package com.weiit.web.admin.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;
import com.weiit.core.service.BaseService;



/**
 * 角色业务接口
 * @author 半个鼠标
 * @date：2017年2月14日 上午2:37:34
 * @version 1.0
 * @company http://www.wei-it.com
 */
public interface RoleService extends BaseService{
	
	List<E> selectRolesByManagerId(FormMap param);
	
	List<E> selectResourceByRoles(List<E> list);
	
	void authorize(FormMap formMap,HttpServletRequest request);
}
