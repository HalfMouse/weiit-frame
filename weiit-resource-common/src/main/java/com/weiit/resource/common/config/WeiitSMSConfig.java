package com.weiit.resource.common.config;

import com.weiit.resource.common.utils.WeiitUtil;

/**
 * 短信运营服务商类-目前支持腾讯云(Qcloud)、容联云(yuntongxun)、阿里云(net)三家短信服务商
 * @author：半个鼠标
 * @date：2018年3月22日
 * @version 1.0
 * @company http://www.wei-it.com 微邦互联
 */
public class WeiitSMSConfig {
	
	public static int smsOpen=2;//open=1表示开启腾讯云短信支持、open=2表示开启阿里云短信支持
	
	/**
	 * 腾讯云短信服务配置
	 */
	public static String smsQcloudAppId="1400175556";//短信应用的APPID
	public static String smsQcloudAppKey="497bb9f33a84db5f91f9cd92834271b9";//短信应用的APPKEY
	public static String smsQcloudSign="卡尔奥斯汽车服务";//短信应用的签名
	
	/**
	 * 阿里云短信服务配置
	 */
	public static String smsAliAccessKeyId = "LTAIXbYXUKxfj2CC";//阿里云短信的AccessKeyId
	public static String smsAliAccessKeySecret = "H8qtdT6gPmAaox6cUNCDFNvlF5r32c";//阿里云短信的AccessKeySecret
	public static String smsAliSign="享够生活";//阿里云短信的签名
	public static  int getSmsOpen() {
		if(WeiitUtil.getPropertiesKey("smsOpen")!=null){
			smsQcloudAppId=WeiitUtil.getPropertiesKey("smsOpen");
		}
		return smsOpen;
	}
	public static  String getSmsQcloudAppId() {
		if(WeiitUtil.getPropertiesKey("smsQcloudAppId")!=null){
			smsQcloudAppId=WeiitUtil.getPropertiesKey("smsQcloudAppId");
		}
		return smsQcloudAppId;
	}
	public static  String getSmsQcloudAppKey() {
		if(WeiitUtil.getPropertiesKey("smsQcloudAppKey")!=null){
			smsQcloudAppKey=WeiitUtil.getPropertiesKey("smsQcloudAppKey");
		}
		return smsQcloudAppKey;
	}
	public static  String getSmsQcloudSign() {
		if(WeiitUtil.getPropertiesKey("smsQcloudSign")!=null){
			smsQcloudSign=WeiitUtil.getPropertiesKey("smsQcloudSign");
		}
		return smsQcloudSign;
	}
	
	public static  String getSmsAliAccessKeyId() {
		if(WeiitUtil.getPropertiesKey("smsAliAccessKeyId")!=null){
			smsAliAccessKeyId=WeiitUtil.getPropertiesKey("smsAliAccessKeyId");
		}
		return smsAliAccessKeyId;
	}
	public static  String getSmsAliAccessKeySecret() {
		if(WeiitUtil.getPropertiesKey("smsAliAccessKeySecret")!=null){
			smsAliAccessKeySecret=WeiitUtil.getPropertiesKey("smsAliAccessKeySecret");
		}
		return smsAliAccessKeySecret;
	}
	public static  String getSmsAliSign() {
		if(WeiitUtil.getPropertiesKey("smsAliSign")!=null){
			smsAliSign=WeiitUtil.getPropertiesKey("smsAliSign");
		}
		return smsAliSign;
	}
	
	
	
}
