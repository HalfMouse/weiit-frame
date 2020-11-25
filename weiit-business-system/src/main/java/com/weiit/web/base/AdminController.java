package com.weiit.web.base;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;





import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.weiit.core.controller.BaseController;
import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;


/**
 * 后端视图控制器
 * 
 * @author 半个鼠标
 * @date：2017年2月4日 上午3:07:07
 * @version 1.0
 * @company http://www.wei-it.com
 */
public class AdminController extends BaseController {
	/**
	 * 直接返回json对象
	 * @param object
	 * @return
	 */
	public String toJson(Object object) {
		return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue,
				SerializerFeature.DisableCircularReferenceDetect);
	}

	/**
	 * 直接返回api接口，默认方法返回成功状态
	 * @param object
	 * @return
	 */
	public String toJsonAPI(Object object) {
		E e = new E();
		e.set("code", "0");
		e.set("message", "success");
		e.set("data", object);
		return JSON.toJSONString(e, SerializerFeature.WriteMapNullValue,
				SerializerFeature.DisableCircularReferenceDetect);
	}

	/**
	 * 直接返回api接口，可自定义返回message,code
	 * @param object
	 * @param message
	 * @param code
	 * @return
	 */
	public String toJsonAPI(Object object, String message, String code) {
		E e = new E();
		e.set("code", code);
		e.set("message", message);
		e.set("data", object);
		return JSON.toJSONString(e, SerializerFeature.WriteMapNullValue,
				SerializerFeature.DisableCircularReferenceDetect);
	}

	// 封装view
	public ModelAndView UIView(String viewName) {
		ModelAndView view = new ModelAndView(viewName);
		return view;
	}

	// 自定义带默认内容View,根据级别返回
	public ModelAndView UIView(String viewName, int level) {
		ModelAndView view = new ModelAndView(viewName);
		Message messageView = new Message();
		return messageView.showView(view, level);
	}

	// 自定义带提示内容View,根据级别返回
	public ModelAndView UIView(String viewName, String message, int level) {
		ModelAndView view = new ModelAndView(viewName);
		Message messageView = new Message();
		return messageView.showView(view, message, level);
	}
	
	//重写BaseController的方法。在原有获取的参数集合里面，统一加上shop_id、app_id参数
	public FormMap getFormMap() {
		FormMap formMap=super.getFormMap();
		//获取当前登录的管理者的所属店铺ID
		if(getSession().getAttribute("shop_id")!=null){
			formMap.set("shop_id", getSession().getAttribute("shop_id"));
		}
		//获取当前登录的管理者所属哪个开发者,开发者=企业的概念
		if(getSession().getAttribute("app_id")!=null){
			formMap.set("app_id", getSession().getAttribute("app_id"));
		} 
		
		return formMap;
	}
	
	//重写BaseController的方法。在原有获取的参数集合里面，统一加上shop_id、app_id参数
	public FormMap getFormMap(boolean isCache) {
		FormMap formMap=super.getFormMap(isCache);
		//获取当前登录的管理者的所属店铺ID
		if(getSession().getAttribute("shop_id")!=null){
			formMap.set("shop_id", getSession().getAttribute("shop_id"));
		}
		//获取当前登录的管理者所属哪个开发者,开发者=企业的概念
		if(getSession().getAttribute("app_id")!=null){
			formMap.set("app_id", getSession().getAttribute("app_id"));
		} 
		return formMap;
	}
	
	
}
