package com.weiit.web.admin.system.controller;
 
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weiit.core.entity.FormMap;
import com.weiit.web.base.AdminController;

/**
 * 相关静态页面控制器
 * @author 半个鼠标
 * @date：2017年2月14日 上午2:01:43
 * @version 1.0
 * @company http://www.wei-it.com
 */
@Controller
public class PageController extends AdminController{
	 
    /**
     * 服务中心后台登录视图
     */
    @RequestMapping("/login")
    public String login() {
        return "/center/login";
    }
    
    /**
     * 后台主页
     * @param request
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "/center/index";
    }
    
    /**
     * 后台布局-左视图
     */
    @RequestMapping("/left")
    public String left() {
        return "/center/left";
    }
    
    /**
     * 后台布局-右视图
     */
    @RequestMapping("/right")
    public String right() {
        return "/center/right";
    }
    
    /**
     * 后台布局-顶部视图
     */
    @RequestMapping("/top")
    public String top() {
        return "/center/top";
    }
    
    /**
     * 后台欢迎视图
     */
    @RequestMapping(value="/welcomePage")
    public String welcomePage(){
    	return "/center/welcome";
    }
    
    /**
     * 控制面板视图
     */
    @RequestMapping("/dashboard")
    public String dashboard() {
        return "/center/dashboard";
    }
    
    /**
     * 404视图
     */
    @RequestMapping("/404")
    public String error404() {
        return "/center/404";
    }

    /**
     * 500视图
     */
    @RequestMapping("/500")
    public String error500() {
        return "/center/500";
    }

    /**
     * 401视图
     */
    @RequestMapping("/401")
    public String error401() { 
    	String requestType = this.getRequest().getHeader("X-Requested-With");  
        // ajax 请求  
        if( this.getRequest().getHeader("accept").contains("application/json") 
        		|| (requestType != null && requestType.equals("XMLHttpRequest")) ){  
            return "redirect:/center/asyn401";  
        } else {  
        	return "redirect:/center/syn401";  
        }
    }
	
    @ResponseBody
    @RequestMapping("/asyn401")
    public String asyn401() { 
    	this.getRequest().setAttribute("logsError", "您没有足够的权限执行该操作!");
        return toJsonAPI("","您没有足够的权限执行该操作!","-1");
    }

    @RequestMapping("/syn401")
    public String syn401() {
    	 return "/center/401"; 
    }
    
    /**
     * error视图
     */
    @RequestMapping("/error")
    public String error() {
    	String requestType = this.getRequest().getHeader("X-Requested-With");  
        // ajax 请求  
    	 if( this.getRequest().getHeader("accept").contains("application/json") 
         		|| (requestType != null && requestType.equals("XMLHttpRequest")) ){  
            return "redirect:/center/asynError";  
        } else {  
            return "redirect:/center/synError";  
        }
    }
    
    @ResponseBody
    @RequestMapping("/asynError")
    public String asynError() { 
    	FormMap formMap=getFormMap(); 
    	this.getRequest().setAttribute("logsError", URLDecoder.decode(formMap.get("logsError").toString()));
        return toJsonAPI("","操作异常，请联系管理员!","-2");
    }

    @RequestMapping("/synError")
    public String synError() {
    	 return "/center/error"; 
    }
}