package com.weiit.core.utils;

import java.util.Date;

/**
 * 
 * @author 半个鼠标
 * @date：2017-8-20 下午4:24:53
 * @version 1.0
 * @company http://www.wei-it.com
 */
public class ValidateUtil {
	
	public static String getValidateToken(String validateId){
		return MD5Util.MD5Encode(validateId+"_token"+new Date().getDay(), "UTF-8");
	}
	
	
	
	
}
