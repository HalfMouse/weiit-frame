package com.weiit.web.admin.system.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;
import com.weiit.core.mapper.BaseMapper;
import com.weiit.core.service.impl.AbstractService;
import com.weiit.web.admin.system.mapper.RoleMapper;
import com.weiit.web.admin.system.service.RoleService;


/**
 * 角色service实现类
 * @author 半个鼠标
 * @date：2017年2月14日 上午2:38:48
 * @version 1.0
 * @company http://www.wei-it.com
 */
@Service
@Transactional(readOnly=false)
public class RoleServiceImpl extends AbstractService implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
	public BaseMapper setMapper() {
		return roleMapper;
	}
    
	@Override
	public List<E> selectRolesByManagerId(FormMap param) {
		return roleMapper.selectRolesByManagerId(param);
	}

	@Override
	public List<E> selectResourceByRoles(List<E> list) {
		return roleMapper.selectResourceByRoles(list);
	}

	/**
	 * 分配角色权限信息(事务)
	 */
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)//事务可写，记住方法中不能捕获异常，捕获异常不会回滚事务，trycatch也不能用
	public void authorize(FormMap formMap, HttpServletRequest request) {
			//删除角色拥有菜单 
			roleMapper.deleteRoleResource(formMap);
			if(((String[])formMap.get("resources")).length>0){
				//插入角色拥有菜单
				roleMapper.insertRoleResource(formMap);
			}
	}


}
