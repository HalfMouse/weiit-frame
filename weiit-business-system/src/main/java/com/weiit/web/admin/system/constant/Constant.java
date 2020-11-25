package com.weiit.web.admin.system.constant;

/**
 * 
 * @author ~~迷途~~
 * @Email：875063678@qq.com
 * @version 1.0
 * @company http://www.wei-it.com
 */
public class Constant {
	/**
	 * 管理员账号类型 1 超管
	 * 2商户
	 * */
	public final static int MANAGER_TYPE_ADMIN=1;
	public final static int MANAGER_TYPE_SHOP=2;
	
	/**
	 * 数据状态 0 有效 -1 无效
	 * */
	public final static int STATE_VALID=0;
	public final static int STATE_INVALID=-1;
	
	/**
	 * 菜单类型 1 左侧菜单 2个人中心菜单 3页面按钮
	 * */
	public final static int RESSOURCE_TYPE_LEFT=1;
	public final static int RESSOURCE_TYPE_USER_CENTER=2;
	public final static int RESSOURCE_TYPE_PAGE_BUTTON=3;
	
	public final static String LOGIN_SESSION_MANAGER="userInfo";
	
}
