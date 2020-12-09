package com.weiit.core.controller;

import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;
import com.weiit.core.view.UIview;

/**
 * 公共视图控制器
 * @author 半个鼠标
 * @date 2017年2月5日 上午3:57:00
 * @version 3.0
 * @company http://www.wei-it.com
 */
public abstract class BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/** ThreadLocal确保高并发下每个请求的request，response都是独立的 */
	private static ThreadLocal<ServletRequest> currentRequest = new ThreadLocal<ServletRequest>();
	private static ThreadLocal<ServletResponse> currentResponse = new ThreadLocal<ServletResponse>();
	/** ThreadLocal确保高并发下每个请求的重定向能依赖redirectAttribute获取传参 */
	private static ThreadLocal<RedirectAttributes> currentRedirectAttributes = new ThreadLocal<RedirectAttributes>();



	@ModelAttribute
	public void initReqAndRep(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) {
		currentRequest.set(request);
		currentResponse.set(response);
		currentRedirectAttributes.set(redirectAttributes);
	}

	protected final HttpServletRequest getRequest() {
		return (HttpServletRequest) currentRequest.get();
	}

	protected final HttpServletResponse getResponse() {
		return (HttpServletResponse) currentResponse.get();
	}

	protected final RedirectAttributes getRedirectAttribute() {
		return (RedirectAttributes) currentRedirectAttributes.get();
	}

	protected final HttpSession getSession() {
		return getRequest().getSession();
	}

	protected final Cookie[] getCookies() {
		return getRequest().getCookies();
	}

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

	/**
	 * 把Get、Post传递的参数进行Map化
	 * @return Map对象
	 */
	@SuppressWarnings("unchecked")
	public FormMap getFormMap() {
		Enumeration<String> en = getRequest().getParameterNames();
		FormMap map = new FormMap();
		try {
			while (en.hasMoreElements()) {
				String key = en.nextElement().toString();
				String v[] = getRequest().getParameterValues(key);
				if (!StringUtils.isEmpty(v) && v.length > 0) {
					if (v.length == 1) {
						map.put(key,new String(v[0].getBytes("utf-8"), "utf-8"));
					} else if (v.length > 1) {
						for(int i=0;i<v.length;i++){
							v[i]=new String(v[i].getBytes("utf-8"), "utf-8");
						}
						map.put(key, v);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 把Get、Post传递的参数进行Map化
	 * @param isCache是boolean类型，传递true、false含义都一样，都表示转化成Map的参数都会绑定到Request中
	 * @return Map对象
	 */
	@SuppressWarnings("unchecked")
	public FormMap getFormMap(boolean isCache) {
		Enumeration<String> en = getRequest().getParameterNames();
		FormMap map = new FormMap();
		try {
			while (en.hasMoreElements()) {
				String key = en.nextElement().toString();
				String v[] = getRequest().getParameterValues(key);
				if (!StringUtils.isEmpty(v) && v.length > 0) {
					if (v.length == 1) {
						map.put(key,new String(v[0].getBytes("utf-8"), "utf-8"));
						getRequest().setAttribute(key, new String(v[0].getBytes("utf-8"), "utf-8"));
					} else if (v.length > 1) {
						for(int i=0;i<v.length;i++){
							v[i]=new String(v[i].getBytes("utf-8"), "utf-8");
						}
						map.put(key, v);
						getRequest().setAttribute(key,v);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 全栈开发-视图返回方式，1转发、2重定向。如果是重定向isRedirect=true
	 * @param viewName
	 * @param isRedirect
	 * @return
	 */
	public UIview UIView(String viewName,boolean isRedirect) {
		if(isRedirect){
			RedirectAttributes attr=getRedirectAttribute();
			UIview view=new UIview(attr);
			view.setViewName("redirect:"+viewName);
			return view;
		}else{
			UIview view=new UIview();
			view.setViewName(viewName);
			return view;
		}
	}
}
