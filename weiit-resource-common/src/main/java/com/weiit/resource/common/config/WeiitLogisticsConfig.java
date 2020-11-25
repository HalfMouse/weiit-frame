package com.weiit.resource.common.config;

import com.weiit.resource.common.utils.WeiitUtil;

/**
 * 物流对接配置-目前支持快递鸟(kuaidiniao)、阿里云两家第三方物流对接
 * @author：半个鼠标(137075251@qq.com)
 * @date：2018年3月22日
 * @version 1.0
 * @company http://www.wei-it.com 微邦互联
 */
public class WeiitLogisticsConfig {
	
	public static String expressOpen="2";//open=1表示开启快递鸟物流对接、open=2表示开启快递100物流对接
	
	/**
	 * 快递鸟物流配置
	 */
	public static String expressKuaidiniaoBusinessId="";//电商ID
	public static String expressKuaidiniaoAppId="";//电商加密私钥，快递鸟提供，注意保管，不要泄漏
	public static String expressKuaidiniaoReqUrl="";//请求url	
	
	/**
	 * 阿里云物流服务
	 */
	public static String expressAliAppKey="25973631";
	public static String expressAliAppSecret="edf703112b53fda337e7ee3c1ec62971";
	public static String expressAppCode="21cb0a4a4b0144d79874a469d81cb585";
	
	public static  String getExpressOpen() {
		if(WeiitUtil.getPropertiesKey("expressOpen")!=null){
			expressOpen=WeiitUtil.getPropertiesKey("expressOpen");
		}
		return expressOpen;
	}
	public static  String getExpressKuaidiniaoBusinessId() {
		if(WeiitUtil.getPropertiesKey("expressKuaidiniaoBusinessId")!=null){
			expressKuaidiniaoBusinessId=WeiitUtil.getPropertiesKey("expressKuaidiniaoBusinessId");
		}
		return expressKuaidiniaoBusinessId;
	}
	public static  String getExpressKuaidiniaoAppId() {
		if(WeiitUtil.getPropertiesKey("expressKuaidiniaoAppId")!=null){
			expressKuaidiniaoAppId=WeiitUtil.getPropertiesKey("expressKuaidiniaoAppId");
		}
		return expressKuaidiniaoAppId;
	}
	public static  String getExpressKuaidiniaoReqUrl() {
		if(WeiitUtil.getPropertiesKey("expressKuaidiniaoReqUrl")!=null){
			expressKuaidiniaoReqUrl=WeiitUtil.getPropertiesKey("expressKuaidiniaoReqUrl");
		}
		return expressKuaidiniaoReqUrl;
	}
	public static  String getExpressAliAppKey() {
		if(WeiitUtil.getPropertiesKey("expressAliAppKey")!=null){
			expressAliAppKey=WeiitUtil.getPropertiesKey("expressAliAppKey");
		}
		return expressAliAppKey;
	}
	public static  String getExpressAliAppSecret() {
		if(WeiitUtil.getPropertiesKey("expressAliAppSecret")!=null){
			expressAliAppSecret=WeiitUtil.getPropertiesKey("expressAliAppSecret");
		}
		return expressAliAppSecret;
	}
	public static  String getExpressAppCode() {
		if(WeiitUtil.getPropertiesKey("expressAppCode")!=null){
			expressAppCode=WeiitUtil.getPropertiesKey("expressAppCode");
		}
		return expressAppCode;
	}
	
	
}
