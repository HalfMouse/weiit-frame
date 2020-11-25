package com.weiit.web.admin.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;
import com.weiit.web.admin.system.constant.Constant;
import com.weiit.web.admin.system.service.ResourceService;
import com.weiit.web.admin.system.service.RoleService;
import com.weiit.web.base.AdminController;
import com.weiit.web.base.Message;

/**
 * 系统管理》角色管理
 * @author 半个鼠标
 * @date：2017年2月14日 上午2:12:48
 * @version 1.0
 * @company http://www.wei-it.com
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends AdminController {
	
	public static Logger logger = Logger.getLogger(RoleController.class);
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private ResourceService resourceService;
	
	private String redirect_list = "redirect:list";
	
    /**
     * 角色管理 》角色列表
     */
    @RequestMapping("/list")
    public ModelAndView list() {
    	logger.info("进入RoleController-list,角色列表");
    	
        ModelAndView result = UIView("/center/system/role");
    	FormMap formMap=getFormMap();
    	//开启分页
    	PageHelper.startPage(formMap.getPage(), formMap.getRows());
    	E userinfo=(E)getSession().getAttribute(Constant.LOGIN_SESSION_MANAGER);
    	formMap.set("manager_id",userinfo.getInt("manager_id"));
    	List<E> roles=roleService.selectRolesByManagerId(formMap);
    	for (E e : roles) {
    		formMap.set("role_id", e.getInt("role_id"));
    		result.addObject("myRoleId", e.getInt("role_id"));
		}
    	List<E> list=roleService.selectList(formMap);
    	
    	logger.info(list.size()+"发现查询的集合并不是所有，而是按照数量查询出来展示，所以不影响性能!");
        result.addObject("pageInfo", new PageInfo<E>(list));
        result.addObject("queryParam", formMap);
        return result;
    }
    
    /**
     * 角色管理 》加载角色信息 
     */
    @ResponseBody
    @RequestMapping("/view")
    @RequiresRoles(value="role")
    @RequiresPermissions(value="role:view")
    public String view() {
    	logger.info("【异步】进入 RoleController-view,加载角色信息");
    	
    	FormMap formMap=getFormMap();
    	E e=roleService.selectOne(formMap);
    	
    	logger.info("【异步返回json】:"+toJsonAPI(e));
        return toJsonAPI(e);
    }
    
    /**
     * 角色管理 》保存角色信息
     */
    @RequestMapping("/save")
    @RequiresRoles(value="role")
    @RequiresPermissions(value="role:save")
    public ModelAndView save() {
    	logger.info("进入 RoleController-save,保存角色信息");
    	
    	ModelAndView result = UIView(redirect_list, Message.INFO);
    	FormMap formMap=getFormMap();
    	E userinfo=(E)getSession().getAttribute(Constant.LOGIN_SESSION_MANAGER);
    	formMap.set("manager_id",userinfo.getInt("manager_id"));
    	List<E> roles=roleService.selectRolesByManagerId(formMap);
    	for (E e : roles) {
    		formMap.set("parent_id", e.getInt("role_id"));
		}
        if (StringUtils.isEmpty(formMap.getStr("validate_id"))) {
        	roleService.insert(formMap);
        } else {
        	roleService.edit(formMap);
        }
        return result;
    }
    
    /**
     *角色管理 》权限分配视图 
     */
    @RequestMapping("/authorize")
    @RequiresRoles(value="role")
    @RequiresPermissions(value="role:authorize")
    public ModelAndView authorize() {
    	logger.info("进入 RoleController-authorize,权限分配视图");
    	
    	FormMap formMap=getFormMap(); 
    	formMap.set("role_id", formMap.get("validate_id"));
    	 ModelAndView result = UIView("/center/system/authorize"); 
    	//当前角色拥有的菜单和操作权限	
        List<E> resourcesList=resourceService.getRoleResources(formMap);
    	E userinfo=(E)getSession().getAttribute(Constant.LOGIN_SESSION_MANAGER);
    	FormMap params=new FormMap();
    	if(userinfo.getInt("type")!=Constant.MANAGER_TYPE_ADMIN){
	    	params.set("manager_id",userinfo.getInt("manager_id"));
	    	List<E> roles=roleService.selectRolesByManagerId(params);
	    	for (E e : roles) {
	    		params.set("role_id", e.getInt("role_id"));
			}
    	}
    	List<E> menuList=resourceService.getResourcesAndButtonList(params);
        //绑定上一次参数
        result.addObject("queryParam", formMap);
        result.addObject("menuList", menuList);
        result.addObject("resourcesList", resourcesList);
        return result;
    }
    
    /**
     * 角色管理 》删除角色 
     */
    @RequestMapping("/remove")
    @RequiresRoles(value="role")
    @RequiresPermissions(value="role:delete")
    public ModelAndView remove() {
    	logger.info("进入 RoleController-remove,删除角色");
    	
    	ModelAndView result = UIView(redirect_list, Message.DANGER);
    	FormMap formMap=getFormMap();
    	roleService.remove(formMap);
        return result;
    }
    
    /**
     * 角色管理 》权限分配保存
     */
    @RequestMapping(value="/saveAuthorize")
    public ModelAndView saveAuthorize(){
    	logger.info("进入 RoleController-saveAuthorize,权限分配保存");
    	
    	ModelAndView result=UIView(redirect_list,Message.INFO);
    	FormMap formMap=getFormMap();
    	roleService.authorize(formMap, getRequest());
    	return result;
    }
}