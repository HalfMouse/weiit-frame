package com.weiit.resource.common.config;

import com.weiit.resource.common.utils.WeiitUtil;

/**
 * 图片存储方式配置-目前支持腾讯云-万象优图(Qcloud)、阿里云(net)
 * @author：半个鼠标(137075251@qq.com)
 * @date：2018年3月22日
 * @version 1.0
 * @company http://www.wei-it.com 微邦互联
 */
public class WeiitFileConfig {
	
	public static String fileUploadOpen="2";//1表示开启腾讯云万象优图存储、2表示开启阿里云oss存储
	
	/**
	 * 腾讯云万象优图配置
	 */
	public static String cosAppId="1253191691";
	public static String cosSecretId="AKIDQjqlbd72NEyXu5cyFOL6riXElDRYJffV";
	public static String cosSecretKey="vcFhxXwCLMAmCQRGvPL6xhHg0Lftvupc";
	public static String cosBucketName="weiyuntest";
	public static String cosRegion="gz";
	public static String cosPre="mytest";
	public static String cosDomain="http://weiyuntest-1253191691.image.myqcloud.com";
	
	/**
	 * 阿里云oss图片存储配置
	 */
	public static String ossAccessKeyId = "LTAIXbYXUKxfj2CC";
	public static String ossAccessKeySecret = "H8qtdT6gPmAaox6cUNCDFNvlF5r32c";
	public static String ossEndPoint = "http://oss-cn-shenzhen.aliyuncs.com";
	public static String ossBucketName = "sharenice";
	public static String ossPre = "life";
	public static String ossDomain = "https://sharenice.oss-cn-shenzhen.aliyuncs.com/";
	
	public static String getFileUploadOpen() {
		if(WeiitUtil.getPropertiesKey("fileUploadOpen")!=null){
			fileUploadOpen=WeiitUtil.getPropertiesKey("fileUploadOpen");
		}
		return fileUploadOpen;
	}
	public static String getCosAppId() {
		if(WeiitUtil.getPropertiesKey("cosAppId")!=null){
			cosAppId=WeiitUtil.getPropertiesKey("cosAppId");
		}
		return cosAppId;
	}
	public static String getCosSecretId() {
		if(WeiitUtil.getPropertiesKey("cosSecretId")!=null){
			cosSecretId=WeiitUtil.getPropertiesKey("cosSecretId");
		}
		return cosSecretId;
	}
	public static String getCosSecretKey() {
		if(WeiitUtil.getPropertiesKey("cosSecretKey")!=null){
			cosSecretKey=WeiitUtil.getPropertiesKey("cosSecretKey");
		}
		return cosSecretKey;
	}
	public static String getCosBucketName() {
		if(WeiitUtil.getPropertiesKey("cosBucketName")!=null){
			cosBucketName=WeiitUtil.getPropertiesKey("cosBucketName");
		}
		return cosBucketName;
	}
	public static String getCosRegion() {
		if(WeiitUtil.getPropertiesKey("cosRegion")!=null){
			cosRegion=WeiitUtil.getPropertiesKey("cosRegion");
		}
		return cosRegion;
	}
	public static String getCosPre() {
		if(WeiitUtil.getPropertiesKey("cosPre")!=null){
			cosPre=WeiitUtil.getPropertiesKey("cosPre");
		}
		return cosPre;
	}
	public static String getCosDomain() {
		if(WeiitUtil.getPropertiesKey("cosDomain")!=null){
			cosDomain=WeiitUtil.getPropertiesKey("cosDomain");
		}
		return cosDomain;
	}
	public static String getOssAccessKeyId() {
		if(WeiitUtil.getPropertiesKey("ossAccessKeyId")!=null){
			ossAccessKeyId=WeiitUtil.getPropertiesKey("ossAccessKeyId");
		}
		return ossAccessKeyId;
	}
	public static String getOssAccessKeySecret() {
		if(WeiitUtil.getPropertiesKey("ossAccessKeySecret")!=null){
			ossAccessKeySecret=WeiitUtil.getPropertiesKey("ossAccessKeySecret");
		}
		return ossAccessKeySecret;
	}
	public static String getOssEndPoint() {
		if(WeiitUtil.getPropertiesKey("ossEndPoint")!=null){
			ossEndPoint=WeiitUtil.getPropertiesKey("ossEndPoint");
		}
		return ossEndPoint;
	}
	public static String getOssBucketName() {
		if(WeiitUtil.getPropertiesKey("ossBucketName")!=null){
			ossBucketName=WeiitUtil.getPropertiesKey("ossBucketName");
		}
		return ossBucketName;
	}
	public static String getOssPre() {
		if(WeiitUtil.getPropertiesKey("ossPre")!=null){
			ossPre=WeiitUtil.getPropertiesKey("ossPre");
		}
		return ossPre;
	}
	public static String getOssDomain() {
		if(WeiitUtil.getPropertiesKey("ossDomain")!=null){
			ossDomain=WeiitUtil.getPropertiesKey("ossDomain");
		}
		return ossDomain;
	}
	
	
	
	
	
	
}
