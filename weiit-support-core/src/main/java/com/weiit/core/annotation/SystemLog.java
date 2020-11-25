package com.weiit.core.annotation;  
  
import java.lang.annotation.*;  
  
/**
 * 自定义注解controller
 * @author 半个鼠标
 * @date 2017年2月5日 上午3:57:00
 * @version 1.0
 * @company http://www.wei-it.com
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public  @interface SystemLog {  
  
	String module()  default "";  //模块名称 系统管理-用户管理－列表页面
	String methods()  default "";  //新增用户
    String description()  default "";  //描述说明
  
  
}  
  
