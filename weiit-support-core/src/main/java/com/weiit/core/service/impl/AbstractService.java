package com.weiit.core.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weiit.core.service.BaseService;
import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;
import com.weiit.core.mapper.BaseMapper;

/**
 * 公共接口抽象类
 * @author 半个鼠标
 * @date 2017年2月5日 上午3:57:00
 * @version 1.0
 * @company http://www.wei-it.com
 */
public abstract class AbstractService implements BaseService{
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public abstract BaseMapper setMapper();
	
	@Override
	public int insert(FormMap param) {
		return setMapper().insert(param);
	}

	@Override
	public int edit(FormMap param) {
		return setMapper().edit(param);
	}

	@Override
	public int remove(FormMap param) {
		return setMapper().remove(param);
	}
	
	@Override
	public int removeBatch(FormMap param) {
		return setMapper().removeBatch(param);
	}

	@Override
	public E selectOne(FormMap param) {
		return setMapper().selectOne(param);
	}

	@Override
	public E selectById(FormMap param) {
		return setMapper().selectById(param);
	}
	
	@Override
	public List<E> selectList(FormMap param) {
		return setMapper().selectList(param);
	}

}
