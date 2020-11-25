package com.weiit.core.entity;
/**
 * 请求参数接收实体类
 * 接收来自GET、POST传递过来的参数，并提供取值与转型。
 * @author 半个鼠标
 * @date 2017年2月5日 上午3:57:00
 * @version 1.0
 * @company http://www.wei-it.com
 */
public class FormMap extends BaseMap<String, Object>{
	private static final long serialVersionUID = 1L;
	/**
	 * 设置分页默认为第一页开始
	 * @return
	 */
	public Integer getPage(){
		return this.get("page")==null?1:Integer.parseInt(this.get("page").toString());
	}
	/**
	 * 设置分页默认为每页展示10条数据
	 * @return
	 */
	public Integer getRows(){
		return this.get("rows")==null?10:Integer.parseInt(this.get("rows").toString());
	}
	
	
}
