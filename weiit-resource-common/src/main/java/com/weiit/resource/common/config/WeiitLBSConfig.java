package com.weiit.resource.common.config;

import com.weiit.resource.common.utils.WeiitUtil;


/**
 * LBS地图服务配置-目前支持百度、微信、高德三家服务商
 * @author：半个鼠标(137075251@qq.com)
 * @date：2018年3月22日
 * @version 1.0
 * @company http://www.wei-it.com 微邦互联
 */
public class WeiitLBSConfig {
	
	public static String lbsOpen="1";//open=1表示开启百度地图集成、open=2表示开启微信地图集成,open=3表示高德地图集成
	
	/**
	 * 百度地图开发者配置
	 */
	public static String lbsBaiduAppId="";//百度地图应用ID
	public static String lbsBaiduAk="";//百度地图应用AK
	
	public static String getLbsOpen() {
		if(WeiitUtil.getPropertiesKey("lbsOpen")!=null){
			lbsOpen=WeiitUtil.getPropertiesKey("lbsOpen");
		}
		return lbsOpen;
	}
	public static String getLbsBaiduAppId() {
		if(WeiitUtil.getPropertiesKey("lbsBaiduAppId")!=null){
			lbsBaiduAppId=WeiitUtil.getPropertiesKey("lbsBaiduAppId");
		}
		return lbsBaiduAppId;
	}
	public static String getLbsBaiduAk() {
		if(WeiitUtil.getPropertiesKey("lbsBaiduAk")!=null){
			lbsBaiduAk=WeiitUtil.getPropertiesKey("lbsBaiduAk");
		}
		return lbsBaiduAk;
	}
	
}
