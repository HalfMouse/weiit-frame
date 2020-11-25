package com.weiit.web.admin.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;
import com.weiit.web.admin.system.service.ManagerService;
import com.weiit.web.base.AdminController;

/**
 * 后台系统》管理员登录
 * @author 半个鼠标
 * @date：2017年2月14日 上午2:12:48
 * @version 1.0
 * @company http://www.wei-it.com
 */
@Controller
@RequestMapping(value = "/test")
public class TestRoleAndTransController extends AdminController{
	@Resource
	private ManagerService managerService;
	
	
	/**
     * 基于角色 标识的权限控制案例
     */
    @RequestMapping(value = "/super")
    @ResponseBody
    @RequiresRoles(value = "SUPER")
    public String admin() {
        return "拥有supper角色,能访问";
    } 
    
    @RequestMapping(value = "/admin")
    @ResponseBody
    @RequiresRoles(value = "ADMIN")
    public String admin2() {
        return "拥有admin角色,能访问";
    } 

    /**
     * 基于权限标识的权限控制案例
     */
    @RequestMapping(value = "/super_delete")
    @ResponseBody
    @RequiresRoles(value = "SUPER")
    @RequiresPermissions(value = "delete")
    public String delete() {
        return "拥有super_delete权限,能访问";
    }
    /**
     * 基于权限标识的权限控制案例
     */
    @RequestMapping(value = "/admin_delete")
    @ResponseBody
    @RequiresPermissions(value = "delete")
    public String create() {
        return "拥有admin_delete权限,能访问";
    }
    
    /**
     * 基于角色 标识的权限控制案例
     */
    @RequestMapping(value = "/trans_read")
    @Transactional(readOnly=true,propagation=Propagation.SUPPORTS)//需要事务操作必须加入此注解,事务只读
    public String trans_read() {
    	FormMap param=getFormMap();
        List<E> list=managerService.selectList(param);
        return "只读事务";
    } 
    /**
     * 基于角色 标识的权限控制案例
     */
    @RequestMapping(value = "/trans_write")
    @Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)//事务可写，记住方法中不能捕获异常，捕获异常不会回滚事务，trycatch也不能用
    public String trans_write() {
    	FormMap param=getFormMap();
    	param.set("roleId", 1);
    	param.set("permissionId", 1);
    	
    	param.clear();
    	param.set("id", "fsadfsad");
    	A();
        return "读写";
    } 
    public int A(){
    	return 1/0;
    }
}
