package com.weiit.core.entity;

import java.util.Date;

import com.weiit.core.utils.ValidateUtil;


/**
 * 数据库操作返回公共实体，并提供取值与转型
 * @author 半个鼠标
 * @date 2017年2月5日 上午3:57:00
 * @version 1.0
 * @company http://www.wei-it.com
 */
public class E extends BaseMap<String, Object>{
	private static final long serialVersionUID = 1L;

	@Override
	public Object put(String key, Object value) {
		if(key.equals("validate_id")){
			super.put(key+"_token", ValidateUtil.getValidateToken(value.toString()));
		}
		return super.put(key, value);
	}	
	
	
	
}
