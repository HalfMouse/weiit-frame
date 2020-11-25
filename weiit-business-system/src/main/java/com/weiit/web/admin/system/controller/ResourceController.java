package com.weiit.web.admin.system.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;
import com.weiit.web.admin.system.service.ResourceService;
import com.weiit.web.base.AdminController;
import com.weiit.web.base.Message;

/**
 * 系统管理》资源管理
 * @author 半个鼠标
 * @date：2017年2月14日 上午2:06:52
 * @version 1.0
 * @company http://www.wei-it.com
 */
@Controller
@RequestMapping("/system/resource")
public class ResourceController extends AdminController{
	
	public static Logger logger = Logger.getLogger(ResourceController.class);
	
	@Resource
	private ResourceService resourceService;
	
	private String redirect_index = "redirect:index";
	
	/**
     * 系统管理》资源菜单视图
     */
	@RequestMapping("/index")
    public String index() {
        return "/center/system/resource";
    }
	
	/**
     * 系统管理》资源菜单列表
     */
	@ResponseBody
    @RequestMapping("/list")
    public String list() {
		logger.info("【异步】进入ResourceController-list,资源菜单列表");
		
    	FormMap formMap=getFormMap();
        List<E> list=resourceService.getResourcesList(formMap);
        
        logger.info("【异步返回json】:"+toJsonAPI(list));
        return toJsonAPI(list);
    }
	
	/**
     * 系统管理》资源菜单编辑
     */
	@ResponseBody
    @RequestMapping("/view")
	@RequiresRoles(value="resource")
    @RequiresPermissions(value="resource:view")
    public String view() {
		logger.info("【异步】进入ResourceController-view,资源菜单编辑");
		
    	FormMap formMap=getFormMap();
        E e=resourceService.selectOne(formMap);
        
        logger.info("【异步返回json】:"+toJsonAPI(e));
        return toJsonAPI(e);
    }
	
	/**
     * 系统管理》资源菜单新增或保存
     */
    @RequestMapping("/save")
    @RequiresRoles(value="resource")
    @RequiresPermissions(value="resource:save")
    public ModelAndView save() {
    	logger.info("进入ResourceController-save,资源菜单保存");
    	
    	ModelAndView view=UIView(redirect_index);
    	FormMap formMap=getFormMap();
    	if(StringUtils.isEmpty(formMap.getStr("validate_id"))){
    		resourceService.insert(formMap);
    	}else{
    		resourceService.edit(formMap);
    	}
        return view;
    }
    
    /**
     * 系统管理》资源菜单删除
     */
    @RequestMapping("/remove")
    @RequiresRoles(value="resource")
    @RequiresPermissions(value="resource:delete")
    public ModelAndView remove() {
    	logger.info("进入ResourceController-remove,资源菜单删除");
    	
    	ModelAndView view=UIView(redirect_index);
    	FormMap formMap=getFormMap();
    	resourceService.remove(formMap);
        return view;
    }
	   
}