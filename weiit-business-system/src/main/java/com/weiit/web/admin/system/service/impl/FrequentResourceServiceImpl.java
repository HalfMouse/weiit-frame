package com.weiit.web.admin.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.weiit.core.entity.FormMap;
import com.weiit.core.mapper.BaseMapper;
import com.weiit.core.service.impl.AbstractService;
import com.weiit.web.admin.system.mapper.FrequentResourceMapper;
import com.weiit.web.admin.system.service.FrequentResourceService;

/**
 * 常用功能server实现类
 * @author ~~迷途~~
 * @date：2017-7-7 上午11:34:22
 * @version 1.0
 * @company http://www.wei-it.com
 */
@Service
@Transactional(readOnly=false)
public class FrequentResourceServiceImpl extends AbstractService implements FrequentResourceService {

	@Resource
	private FrequentResourceMapper frequentResourceMapper;
	
	@Override
	public BaseMapper setMapper() {
		return frequentResourceMapper;
	}
	
	/**
	 * 保存用户常用功能设置资源信息
	 */
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)//事务可写，记住方法中不能捕获异常，捕获异常不会回滚事务，trycatch也不能用
	public void saveFrequentResourceTrans(FormMap formMap) {
		//保存时先对数据进行删除 然后再插入，此处insert语句为批量插入语句
		frequentResourceMapper.remove(formMap);
		if(formMap.get("resource_id")!=null){
			frequentResourceMapper.insert(formMap);
		}

	}

	

}
