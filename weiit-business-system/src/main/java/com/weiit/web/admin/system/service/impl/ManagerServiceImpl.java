package com.weiit.web.admin.system.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;
import com.weiit.core.mapper.BaseMapper;
import com.weiit.core.service.impl.AbstractService;
import com.weiit.web.admin.system.constant.Constant;
import com.weiit.web.admin.system.mapper.ManagerMapper;
import com.weiit.web.admin.system.service.ManagerService;



/**
 * 管理员service实现类
 * @author 半个鼠标
 * @date：2017年2月14日 上午2:37:50
 * @version 1.0
 * @company http://www.wei-it.com
 */
@Service
@Transactional(readOnly=false)
public class ManagerServiceImpl extends AbstractService implements ManagerService {

    @Resource
    private ManagerMapper managerMapper;

	@Override
	public BaseMapper setMapper() {
		return managerMapper;
	}

	@Override
	public E login(FormMap formMap){
		return managerMapper.login(formMap);
	}

	@Override
	public void editManagerPassword(FormMap formMap) {
		managerMapper.editManagerPassword(formMap);
	}

	/**
	 * 管理员创建(事务)
	 */
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)//事务可写，记住方法中不能捕获异常，捕获异常不会回滚事务，trycatch也不能用
	public void createTrans(FormMap param) {
		//插入管理员信息， 获得管理员id 再插入管理员与角色的关系数据
		managerMapper.insert(param);//此时param中存在manager_id参数返回
		String [] roleSigns=(String [])param.get("roleSigns");
		for (String role_id : roleSigns) {
			param.set("role_id", role_id);
			managerMapper.insertManagerAndRole(param);
		}
		
	}

	/**
	 * 修改管理员信息(事务)
	 */
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)//事务可写，记住方法中不能捕获异常，捕获异常不会回滚事务，trycatch也不能用
	public void updateTrans(FormMap param) {
		//移除管理员与角色关系数据
		managerMapper.removeManagerAndRole(param);
		//修改管理员信息
		managerMapper.edit(param);
		param.put("manager_id", param.get("validate_id"));
		String [] roleSigns=(String [])param.get("roleSigns");
		for (String role_id : roleSigns) {
			param.set("role_id", role_id);
			//插入管理员与角色信息数据
			managerMapper.insertManagerAndRole(param);
		}
		
	}

	/**
	 * 删除管理员(事务)
	 */
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)//事务可写，记住方法中不能捕获异常，捕获异常不会回滚事务，trycatch也不能用
	public void deleteTrans(FormMap param) {
		//删除管理员
		managerMapper.remove(param);
		//删除管理员与角色关系数据
		managerMapper.removeManagerAndRole(param);
	}
	
}
