package com.weiit.web.admin.system.mapper;

import java.util.List;


import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;
import com.weiit.core.mapper.BaseMapper;


/**
 * 角色mapper接口
 * @author 半个鼠标
 * @date：2017年2月14日 上午2:35:53
 * @version 1.0
 * @company http://www.wei-it.com
 */
public interface RoleMapper extends BaseMapper{
	
	/**
	 * 根据管理员id查询管理员拥有的角色集合
	 * @param param
	 * @return 角色集合
	 */
	List<E> selectRolesByManagerId(FormMap param);
	
	/**
	 * 根据角色集合 查询资源集合
	 * @param list 角色集合
	 * @return 资源集合
	 */
	List<E> selectResourceByRoles(List<E> list);
	
	/**
	 * 插入角色与资源关系数据
	 * @param formMap
	 */
	void insertRoleResource(FormMap formMap);
	
	/**
	 * 删除角色与资源关系数据
	 * @param formMap
	 */
	void deleteRoleResource(FormMap formMap);
}