package com.weiit.web.admin.system.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;
import com.weiit.core.mapper.BaseMapper;
import com.weiit.core.service.impl.AbstractService;
import com.weiit.web.admin.system.mapper.ResourceMapper;
import com.weiit.web.admin.system.service.ResourceService;


/**
 * 资源管理service实现类
 * @author 半个鼠标
 * @date：2017年2月14日 上午2:38:48
 * @version 1.0
 * @company http://www.wei-it.com
 */
@Service
@Transactional(readOnly=false)
public class ResourceServiceImpl extends AbstractService implements ResourceService {

    @Resource
    private ResourceMapper resourceMapper;

    @Override
	public BaseMapper setMapper() {
		return resourceMapper;
	}
    
	public void updateState(FormMap formMap){
		resourceMapper.updateState(formMap);
	}

	@Override
	public List<E> getRoleResources(FormMap formMap) {
		return resourceMapper.selectList(formMap);
	}
	@Override
	public List<E> getResourcesList(FormMap formMap) {
		List<E> resourcesList=resourceMapper.selectList(formMap);
		return getChildList(-1, resourcesList);
	}

	
	
	@Override
	public List<E> getResourcesAndButtonList(FormMap formMap) {
		   List<E> resourcesList=resourceMapper.selectList(formMap);
			return getChildListAndButton(-1, resourcesList);
	}

	/**
	 * 将集合分级，做成树型结构数据
	 */
	public List<E> getChildList(int resourcesId,List<E> menuList){
		   List<E> childList=new ArrayList<E>();
		   for (E e : menuList) {
			   	if(e.getInt("parent_id")==resourcesId){
				   		e.set("children", getChildList(e.getInt("resource_id"), menuList));
				   		childList.add(e);
			   	}
		   }
		   return childList;
	 }
	
	public List<E> getChildListAndButton(int resourcesId,List<E> menuList){
		   List<E> childList=new ArrayList<E>();
		   for (E e : menuList) {
			   	if(e.getInt("parent_id")==resourcesId){
			   		if(e.getInt("type")==1||e.getInt("type")==2){
				   		e.set("children", getChildListAndButton(e.getInt("resource_id"), menuList));
				   		e.set("childrenButton",getChildButtonList(e.getInt("resource_id"), menuList));
				   		childList.add(e);
			   		}
			   	}
		   }
		   return childList;
	}
	
	/**
	 * 获得菜单下按钮集合
	 */
	public List<E> getChildButtonList(int resourcesId,List<E> menuList){
		   List<E> childButtonList=new ArrayList<E>();
		   for (E e : menuList) {
			   	if(e.getInt("parent_id")==resourcesId){
			   		if(e.getInt("type")==3){
				   		childButtonList.add(e);
			   		}
			   	}
		   }
		   return childButtonList;
	   } 
}
