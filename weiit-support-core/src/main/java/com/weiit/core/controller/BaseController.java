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

import com.weiit.core.entity.FormMap;

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

	/**V3.0版本，支持application/x-www-form-urlencoded、application/json 两种参数获取
	 * 把Get、Post传递的参数进行Map化
	 * @return Map对象
	 
	@SuppressWarnings("unchecked")
	public FormMap getFormMap() {
		FormMap map = new FormMap();
		try {
			//获取application/json格式的参数，把json内容统一作为data存储
			BufferedReader reader = new BufferedReader(new InputStreamReader(getRequest().getInputStream()));
			FormMap data=JSONObject.parseObject(WeiitUtil.getBodyString(reader),FormMap.class);
			map.put("data", data);
			
			//获取application/x-www-form-urlencoded 方式传递过来的参数，按照key-value存储
			Enumeration<String> en = getRequest().getParameterNames();
			while (en.hasMoreElements()) {
				String key = en.nextElement().toString();
				String v[] = getRequest().getParameterValues(key);
				if (!StringUtils.isEmpty(v) && v.length > 0) {
					if (v.length == 1) {
						map.put(key,new String(v[0].getBytes("ISO8859-1"), "UTF-8"));
					} else if (v.length > 1) {
						for(int i=0;i<v.length;i++){
							v[i]=new String(v[i].getBytes("ISO8859-1"), "UTF-8");
						}
						map.put(key, v);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}*/
	
	/**V2.0版本，只支持application/x-www-form-urlencoded 参数获取
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
}
