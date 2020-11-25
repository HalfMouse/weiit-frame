package com.weiit.core.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * 参数实体
 * @author 半个鼠标
 * @date 2017年2月5日 上午3:57:00
 * @version 1.0
 * @company http://www.wei-it.com
 */
public class BaseMap<K, V> extends HashMap<K, V> implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	public void set(K key,V value){
		this.put(key, value);
	}
	
	public String getStr(String attr) {
		return this.get(attr)==null?"":this.get(attr).toString();
	}

	public Integer getInt(String attr) {
		return  this.get(attr)==null?0:Integer.parseInt(this.get(attr).toString());
	}

	public Long getLong(String attr) {
		return this.get(attr)==null?0L:Long.parseLong(this.get(attr).toString());
	}
	
	public Double getDouble(String attr) {
		return this.get(attr)==null?0.0d:Double.parseDouble(this.get(attr).toString());
	}

	public Float getFloat(String attr) {
		return this.get(attr)==null?0.0f:Float.parseFloat(this.get(attr).toString());
	}

	public Boolean getBoolean(String attr) {
		return this.get(attr)==null?false:Boolean.parseBoolean(this.get(attr).toString());
	}
	
	public java.math.BigInteger getBigInteger(String attr) {
		return (java.math.BigInteger) this.get(attr);
	}

	public java.util.Date getDate(String attr) {
		return (java.util.Date) this.get(attr);
	}
	
	public java.sql.Time getTime(String attr) {
		return (java.sql.Time) this.get(attr);
	}

	public java.sql.Timestamp getTimestamp(String attr) {
		return (java.sql.Timestamp) this.get(attr);
	}

	public java.math.BigDecimal getBigDecimal(String attr) {
		return (java.math.BigDecimal) this.get(attr);
	}

	public byte[] getBytes(String attr) {
		return (byte[]) this.get(attr);
	}

	public Number getNumber(String attr) {
		return (Number) this.get(attr);
	}
	
	public String[] getAttrNames() {
		Set<K> attrNameSet =this.keySet();
		return attrNameSet.toArray(new String[attrNameSet.size()]);
	}

	public Object[] getAttrValues() {
		Collection<V> attrValueCollection = values();
		return attrValueCollection.toArray(new Object[attrValueCollection
				.size()]);
	}

}
