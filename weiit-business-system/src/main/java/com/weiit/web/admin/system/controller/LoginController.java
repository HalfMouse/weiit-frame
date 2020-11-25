package com.weiit.web.admin.system.controller;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;
import com.weiit.core.utils.MD5Util;
import com.weiit.web.admin.system.constant.Constant;
import com.weiit.web.admin.system.service.ManagerService;
import com.weiit.web.admin.system.service.ResourceService;
import com.weiit.web.base.AdminController;
import com.weiit.web.base.Message;


/**
 * 后台系统》管理员登录
 * @author 半个鼠标
 * @date：2017年2月14日 上午2:12:48
 * @version 1.0
 * @company http://www.wei-it.com
 */
@Controller
@RequestMapping(value = "/manager")
public class LoginController extends AdminController{
	public static Logger logger = Logger.getLogger(LoginController.class);
	
    @Resource
    private ManagerService managerService;
    
    @Resource
	private ResourceService resourceService;
   
    /**
     * 管理员登录
     */
    @RequestMapping("/login")
    public ModelAndView login() {
    	logger.info("进入LoginController-login,管理员登录");
    	
    	ModelAndView result=new ModelAndView();
    	FormMap formMap=getFormMap();
        try {
            Subject subject = SecurityUtils.getSubject();
            // 已登陆则 跳到首页
            if (subject.isAuthenticated()) {
            	result.setViewName("redirect:/center/index") ;
            }
            logger.info("开始进行身份验证");
            subject.login(new UsernamePasswordToken(formMap.getStr("account_name"), MD5Util.MD5Encode(formMap.getStr("password"),"utf-8")));
            logger.info("身份验证成功，将用户信息保存到session中");
            E authUserInfo=managerService.selectOne(formMap);
            getSession().setAttribute("sessionUserKey", Constant.LOGIN_SESSION_MANAGER);
            getSession().setAttribute(Constant.LOGIN_SESSION_MANAGER, authUserInfo);
            
            logger.info("开始加载菜单资源，将菜单资源保存到session中");
            FormMap params=new FormMap();
            params.put("state", Constant.STATE_VALID);
            params.put("type",Constant.RESSOURCE_TYPE_LEFT);
            List<E> list=resourceService.selectList(params);
            List<E> menuList = new ArrayList<E>();
            params.put("type", Constant.RESSOURCE_TYPE_USER_CENTER);
            List<E> userIndexList=resourceService.selectList(params);
            for (E menu : list) {
            	if(menu.getInt("parent_id")==-1&&menu.getInt("type")==1){
            		menuList.add(menu);
            	}
            }
            for (E menu : menuList) {
                menu.set("children", getChild(menu.getInt("resource_id"), list));
            }
            getSession().setAttribute("menuList", menuList);
            getSession().setAttribute("userIndexList", userIndexList);
            
            logger.info("管理员登录成功,登录账户 :"+formMap.getStr("account_name"));
            result.setViewName("redirect:/center/index") ;
        } catch (AuthenticationException e) {
            // 身份验证失败
        	result.addObject("info", "用户名或密码错误 ！");
        	result.setViewName("/center/login") ;
        }
        return result;
    }

    /**
     * 管理员注销
     */
    @RequestMapping("/logout")
    public String logout() {
    	logger.info("进入LoginController-logout,管理员注销");
    	
        getSession().removeAttribute(Constant.LOGIN_SESSION_MANAGER);
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/center/login";
    }
    
    /**
     * 管理员修改密码视图
     */
    @RequestMapping(value="/updatePasswordUI")
    public String updatePasswordUI(){
    	return "/center/updatePassword";
    }
    
    /**
     * 管理员修改密码-提交
     */
    @RequestMapping(value="/updatePassword")
    public ModelAndView updatePassword(){
    	logger.info("进入LoginController-updatePassword,管理员修改密码");
    	
    	FormMap formMap=getFormMap();
    	ModelAndView result = UIView("/center/updatePassword", Message.DANGER);
    	E userInfo=(E)getSession().getAttribute(Constant.LOGIN_SESSION_MANAGER);
    	String message="";
    	boolean flag=true;
    	if(!userInfo.getStr("password").equals(MD5Util.MD5Encode(formMap.getStr("oldPassword"), "utf-8"))){
    		message="您输入的原密码不正确";
    		flag=false;
    	}else if(formMap.getStr("password")==null || formMap.getStr("password")==""){
    		message="您输入的新密码为空";
    		flag=false;
    	}else if(!formMap.getStr("password").equals(formMap.getStr("confirmPassword"))){
    		message="您输入的确认密码不一致";
    		flag=false;
    	}
    	if(flag){
    		formMap.set("password", MD5Util.MD5Encode(formMap.getStr("password"), "utf-8"));
	    	managerService.editManagerPassword(formMap);
	    	userInfo.set("password", formMap.getStr("password"));
	    	getSession().setAttribute(Constant.LOGIN_SESSION_MANAGER, userInfo);
	    	message="您的密码已修改成功，请牢记您的密码";
    	}else{
    		result.addObject("oldPassword", formMap.getStr("oldPassword"));
    		result.addObject("password", formMap.getStr("password"));
    		result.addObject("confirmPassword", formMap.getStr("confirmPassword"));
    	}
    	result.addObject("notifyMessage", message);
    	return result;
    }
    
    /**
     * 递归查询菜单结构
     */
    private List<E> getChild(int resourceId, List<E> rootMenu) {
	    List<E> childList = new ArrayList<E>();
	    for (E e : rootMenu) {
	    	if(e.getInt("parent_id")!=-1){
	    		if (e.getInt("parent_id").equals(resourceId)) {
		            childList.add(e);
		        }
	    	}
	    }
	    for (E e : childList) {
	        e.set("children", getChild(e.getInt("resource_id"), rootMenu));
	    } 
	    if (childList.size() == 0) {
	        return new ArrayList<E>();
	    }
	    return childList;
	}
   
    
}
