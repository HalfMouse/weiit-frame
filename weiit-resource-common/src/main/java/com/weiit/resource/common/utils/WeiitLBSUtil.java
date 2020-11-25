package com.weiit.resource.common.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * @author 半个鼠标
 * @Email：137075251@qq.com
 * @date：2017年2月24日 上午1:24:32
 * @version 1.0
 */
public class WeiitLBSUtil {
	
	public static int getLocationInfo(String origins,String destinations,String ak){
		String getUrl = "http://api.map.baidu.com/routematrix/v2/walking?output=json&origins="+origins+"&destinations="+destinations+"&ak="+ak;   
		String response = ""; 
		try {
			URL url = new URL(getUrl);   
			HttpURLConnection uRLConnection = (HttpURLConnection)url.openConnection();   
			InputStream is = uRLConnection.getInputStream();   
			BufferedReader br = new BufferedReader(new InputStreamReader(is));   
			String readLine = null;   
			while((readLine =br.readLine()) != null){   
			response = response + readLine;   
			}   
			is.close();   
			br.close();   
			uRLConnection.disconnect();   
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}   
		System.out.println(response);
		Map<String, Object> map = JSON.parseObject(
                response,new TypeReference<Map<String, Object>>(){} );
		Object obj=map.get("result");
		List list=JSON.parseArray(obj.toString());
		Map<String, Object> result = JSON.parseObject(
				list.get(0).toString(),new TypeReference<Map<String, Object>>(){} );
		Map<String, Object> distance = JSON.parseObject(
				result.get("distance").toString(),new TypeReference<Map<String, Object>>(){} );
		return Integer.parseInt(distance.get("value").toString());
	}
	
	public static void main(String[] args) throws Exception {
		
		String getUrl = "http://api.map.baidu.com/routematrix/v2/walking?output=json&origins=22.642491,114.032412|22.643367,114.031949&destinations=40.34,116.45|40.35,116.46&ak=loGEk2WmUNlT0NYYwEvSjWNnvFda2IqZ";   
		   
		int julu =getLocationInfo("22.642491,114.032412","22.643367,114.031949","loGEk2WmUNlT0NYYwEvSjWNnvFda2IqZ");
		System.out.println(julu);
		
	}
}
