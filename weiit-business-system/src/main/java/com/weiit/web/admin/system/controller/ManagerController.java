package com.weiit.web.admin.system.controller;


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
import com.weiit.core.utils.MD5Util;
import com.weiit.web.admin.system.constant.Constant;
import com.weiit.web.admin.system.service.ManagerService;
import com.weiit.web.admin.system.service.RoleService;
import com.weiit.web.base.AdminController;
import com.weiit.web.base.Message;


/**
 * 系统管理》管理员列表
 * @author 半个鼠标
 * @date：2017年7月14日 上午2:12:48
 * @version 1.0
 * @company http://www.wei-it.com
 */
@Controller
@RequestMapping(value = "/system/manager")
public class ManagerController extends AdminController{
	
	public static Logger logger = Logger.getLogger(ManagerController.class);
	
    @Resource
    private ManagerService managerService;
    
    @Resource
    private RoleService roleService;
    
	private String redirect_list = "redirect:list";
	
	/**
     * 系统管理》管理员列表
     */
    @RequestMapping("/list")
    public ModelAndView list() {
    	logger.info("进入ManagerController-list,管理员列表");
    	
    	ModelAndView result = UIView("/center/system/manager");
    	FormMap formMap=getFormMap();
    	E userinfo=(E)getSession().getAttribute(Constant.LOGIN_SESSION_MANAGER);
    	formMap.set("manager_id",userinfo.getInt("manager_id"));
    	//开启分页
    	PageHelper.startPage(formMap.getPage(), formMap.getRows());
    	List<E> list=managerService.selectList(formMap);
    	
    	logger.info(list.size()+"发现查询的集合并不是所有，而是按照数量查询出来展示，所以不影响性能!");
    	//查询角色列表
    	FormMap roleFormMap=new FormMap();
    	roleFormMap.set("manager_id",userinfo.getInt("manager_id"));
    	List<E> roles=roleService.selectRolesByManagerId(roleFormMap);
    	for (E e : roles) {
			roleFormMap.set("role_id", e.getInt("role_id"));
		}
    	List<E> roleList=roleService.selectList(roleFormMap);
        result.addObject("pageInfo", new PageInfo<E>(list));
        result.addObject("queryParam", formMap);
        result.addObject("roleList",roleList);
        return result;
    }
    
    /**
     * 系统管理》管理员列表-保存与新增操作
     */
    @RequestMapping("/save")
    @RequiresRoles(value="manager")
    @RequiresPermissions(value="manager:save")
    public ModelAndView save() {
    	logger.info("进入ManagerController-save,管理员保存或编辑");
    	
    	ModelAndView result = UIView(redirect_list, Message.INFO);
    	FormMap formMap=getFormMap();
    	String [] roleSigns=getRequest().getParameterValues("role_sign");
    	E userinfo=(E)getSession().getAttribute(Constant.LOGIN_SESSION_MANAGER);
    	formMap.set("parent_id",userinfo.getInt("manager_id"));
    	formMap.set("type", Constant.MANAGER_TYPE_SHOP);
    	//用户密码md5加密
    	formMap.set("password", MD5Util.MD5Encode(formMap.getStr("password"),"utf-8"));
        if (StringUtils.isEmpty(formMap.getStr("validate_id"))) {
        	formMap.set("roleSigns", roleSigns);
        	managerService.createTrans(formMap);//事务方法
        } else {
        	formMap.set("roleSigns", roleSigns);
        	managerService.updateTrans(formMap);//事务方法
        }
        return result;
    }
    
    /**
     * 系统管理》管理员列表-删除操作
     */
    @RequestMapping("/remove")
    @RequiresRoles(value="manager")
    @RequiresPermissions(value="manager:delete")
    public ModelAndView remove() {
    	logger.info("进入ManagerController-remove,管理员删除");
    	
    	ModelAndView result = UIView(redirect_list, Message.DANGER);
    	FormMap formMap=getFormMap();
    	managerService.deleteTrans(formMap);
        return result;
    }
    
    /**
     * 系统管理》管理员列表-修改密码操作
     */
    @RequestMapping(value="/editManagerPassword")
    public ModelAndView editManagerPassword(){
    	logger.info("进入ManagerController-editManagerPassword,管理员修改密码");
    	
    	ModelAndView result = UIView(redirect_list, Message.DANGER);
    	FormMap formMap=getFormMap();
    	formMap.set("password", MD5Util.MD5Encode(formMap.getStr("password"), "utf-8"));
    	managerService.editManagerPassword(formMap);
    	return result;
    }
    
    /**
     * 异步返回(查询所有角色列表)
     */
    @ResponseBody
    @RequestMapping("/selectRoles")
    public String selectRoles() {
    	logger.info("【异步】进入ManagerController-selectRoles,查询所有角色列表");
    	
    	List<E> roleList=roleService.selectList(null);
    	
    	logger.info("【异步json返回】:"+toJson(roleList));
    	return toJson(roleList);
    }
    
    /**
     * 异步返回(根据管理员查询管理员的角色)
     */
    @ResponseBody
    @RequestMapping("/selectRolesByManagerId")
    public String selectRolesByManagerId() {
    	logger.info("【异步】进入ManagerController-selectRolesByManagerId,根据管理员查询管理员的角色");
    	
    	FormMap formMap=getFormMap();
    	List<E> list=roleService.selectRolesByManagerId(formMap);
    	
    	logger.info("【异步json返回】:"+toJson(list));
    	
    	return toJson(list);
    }
    
    /**
     * 异步返回(查询管理员信息)
     */
    @ResponseBody
    @RequestMapping("/view")
    @RequiresRoles(value="manager")
    @RequiresPermissions(value="manager:view")
    public String view() {
    	logger.info("【异步】进入ManagerController-view,查询管理员信息");
    	
    	FormMap formMap=getFormMap();
    	E e=managerService.selectOne(formMap);
    	
    	logger.info("【异步json返回】:"+toJsonAPI(e));
    	
        return toJsonAPI(e);
    }
    
}
