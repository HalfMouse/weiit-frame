package com.weiit.web.admin.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weiit.core.entity.E; 
import com.weiit.core.entity.FormMap;
import com.weiit.web.admin.system.constant.Constant;
import com.weiit.web.admin.system.service.FrequentResourceService;
import com.weiit.web.base.AdminController;

/**
 * 后台系统》常用功能
 * @author ~~迷途~~
 * @date：2017-7-7 上午11:41:13
 * @version 1.0
 * @company http://www.wei-it.com
 */
@Controller
@RequestMapping(value="/system/frequent")
public class FrequentResourceController extends AdminController {
	
	public static Logger logger = Logger.getLogger(FrequentResourceController.class);
	
	@Resource
	private FrequentResourceService frequentResourceService;
	
	/**
	 * 常用功能》视图加载
	 */
	@ResponseBody
	@RequestMapping(value="/getFrequentResource")
	public String getFrequentResource(){
		logger.info("【异步】进入FrequentResourceController-getFrequentResource,获取管理员常用功能设置");
		
		FormMap formMap=new FormMap();
		E userInfo=(E)getSession().getAttribute(Constant.LOGIN_SESSION_MANAGER);
		formMap.set("manager_id", userInfo.getInt("manager_id"));
		List<E> list=frequentResourceService.selectList(formMap);
		
		logger.info("【异步返回json】:"+toJson(list));
		return toJson(list);
	}
	
	/**
	 * 常用功能 》保存
	 */
	@ResponseBody
	@RequestMapping(value="/saveFrequentResource")
	public String saveFrequentResource(){
		logger.info("【异步】进入FrequentResourceController-saveFrequentResource,保存管理员常用功能设置");
		
		FormMap formMap=getFormMap();
		String[] resource_id=(String[])formMap.get("resource_id[]");
		formMap.set("resource_id", resource_id);
		E userInfo=(E)getSession().getAttribute(Constant.LOGIN_SESSION_MANAGER);
		formMap.set("manager_id", userInfo.getInt("manager_id"));
		frequentResourceService.saveFrequentResourceTrans(formMap);
		E e=new E();
		e.set("status", "success");
		
		logger.info("【异步返回json】:"+toJson(e));
		return toJson(e);
		
	}
	
}
