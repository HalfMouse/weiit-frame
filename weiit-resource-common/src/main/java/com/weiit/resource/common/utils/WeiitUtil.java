package com.weiit.resource.common.utils;


import java.io.IOException;




import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import com.aliyuncs.http.HttpResponse;
import com.weiit.resource.common.config.WeiitFileConfig;
import com.weiit.resource.common.config.WeiitLogisticsConfig;
import com.weiit.resource.common.config.WeiitSMSConfig;

import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;
/**
 * 微邦互联工具类
 * 
 * @author 半个鼠标
 * @date：2017年2月14日 上午2:12:48
 * @version 1.0
 * @company http://www.wei-it.com
 */
public class WeiitUtil {

	/**
	 * 短信公共方法，具体要依据WeiitSMSConfig的open值决定调用哪个服务商的短信支持
	 * 
	 * @param mobileNums表示手机号码
	 *            ，可以是多个，多个用逗号分开，比如“15622720546,18565660736”
	 * @param templateId表示短信模板id
	 *            ，模板中存在{1}，{2}这样的占位符
	 * @param contents
	 *            表示需要替换的内容，是一个字符串数组，每个字符串数组按照顺利会替换模板中的占位符{1}，{2}
	 * @return 发送成功或者发送失败
	 */
	public static boolean sendMobileMessage(String mobileNums,
			String templateId, String[] contents) {
		
		switch (WeiitSMSConfig.getSmsOpen()) {
		case 1:
			return WeiitSMSUtil.sendMobileMessageByQcloud(mobileNums,
					templateId, contents);
		case 2:
			return WeiitSMSUtil.sendMobileMessageByAliyun(mobileNums,
					templateId, contents[0]);
		default:
			return false;
		}
		
	}
	
	
	/**
	 * 图片存储公共方法，具体要依据WeiitImageConfig的open值决定图片存储方式
	 * @param data 表示图片的字节流
	 * @return 图片上传后端图片地址
	 */
	public static String uploadFile(byte[] data, String fileFormat) throws IOException  {
		
		WeiitFileUtil util=new WeiitFileUtil();
		
		switch (Integer.parseInt(WeiitFileConfig.getFileUploadOpen())) {
		case 1:
			return util.uploadFileByQcloud(data,fileFormat);
		case 2:
			return util.uploadFileByAliyun(data,fileFormat);
		default:
			return null;
		}
		
	}
	public static String uploadFile(byte[] data, String fileFormat,String cosFilePath) throws IOException  {
			
			WeiitFileUtil util=new WeiitFileUtil();
			
			switch (Integer.parseInt(WeiitFileConfig.getFileUploadOpen())) {
			case 1:
				return util.uploadFileByQcloud(data,fileFormat,cosFilePath);
			case 2:
				return util.uploadFileByAliyun(data,fileFormat,cosFilePath);
			default:
				return null;
			}
			
		}
	
	/**
	 * 图片存储公共方法，具体要依据WeiitImageConfig的open值决定图片存储方式
	 * @param file MultipartFile
	 * @return 图片上传后端图片地址
	 * @throws IOException 
	 */
	public static String uploadFile(MultipartFile file) throws IOException {
		
		WeiitFileUtil util=new WeiitFileUtil();
		
		switch (Integer.parseInt(WeiitFileConfig.getFileUploadOpen())) {
		case 1:
			return util.uploadFileByQcloud(file);
		case 2:
			return util.uploadFileByAliyun(file);
		default:
			return null;
		}
		
	}
	
	public static String uploadFile(MultipartFile file,String cosFilePath) throws IOException {
			
			WeiitFileUtil util=new WeiitFileUtil();
			
			switch (Integer.parseInt(WeiitFileConfig.getFileUploadOpen())) {
			case 1:
				return util.uploadFileByQcloud(file,cosFilePath);
			case 2:
				return util.uploadFileByAliyun(file,cosFilePath);
			default:
				return null;
			}
			
		}
	
	public static String getFileDomain() {
		
		switch (Integer.parseInt(WeiitFileConfig.getFileUploadOpen())) {
		case 1:
			return WeiitFileConfig.getCosDomain();
		case 2:
			return WeiitFileConfig.getOssDomain();
		default:
			return null;
		}
		
	}
	
	/**
	 * 物流公共查询方法，具体要依据WeiitLogisticsConfig的open值决定走哪家通道查询
	 * @param expCode 物流公司编号
	 * @param expNo 物流单号
	 * @return 物流轨迹信息
	 */
	public static String getLogistics(String expCode, String expNo) throws Exception{
		
		switch (Integer.parseInt(WeiitLogisticsConfig.getExpressOpen())) {
		case 1:
			return WeiitLogisticsUtil.getOrderTracesByJson(expCode, expNo);
		case 2:
			return WeiitLogisticsUtil.getExpressInfoByAli(expCode, expNo);
		default:
			return null;
		}
		
	}
	
	/**
	 * 读取配置文件方法
	 * @param key
	 * @return
	 */
	public static String getPropertiesKey(String key){
		Properties prop = new Properties();   
        InputStream in = WeiitUtil.class.getClassLoader().getResourceAsStream("resource.properties");   
        try {   
        	if(in!=null){
        		 prop.load(in);
        		 if ( prop.getProperty(key)==null){
        		 	return null;
				 }
                 return prop.getProperty(key).trim(); 
        	}
        	return null;
             
        } catch (IOException e) {   
            e.printStackTrace();   
            return null;
        }  
		
	}
	
	/**
	 * 读取配置文件
	 * @param filePath
	 * @param key
	 * @return
	 */
	public static String getPropertiesKey(String filePath,String key){
		Properties prop = new Properties();   
        InputStream in = Object.class.getResourceAsStream(filePath);   
        try {   
            prop.load(in);   
            return prop.getProperty(key).trim();   
        } catch (IOException e) {   
            e.printStackTrace();   
            return null;
        }  
		
	}
	
	public static void main(String[] args) {
//	    String host = "https://wdexpress.market.alicloudapi.com";
//	    String path = "/gxali";
//	    String method = "GET";
//	    String appcode = "5a522f64fbfc4268a4afd21d06d433b7";
//	    Map<String, String> headers = new HashMap<String, String>();
//	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//	    headers.put("Authorization", "APPCODE " + appcode);
//	    Map<String, String> querys = new HashMap<String, String>();
//	    querys.put("n", "780098068058");
//	    querys.put("t", "zto");
//            //JDK 1.8示例代码请在这里下载：  http://code.fegine.com/Tools.zip
//
//	    try {
//	    	/**
//	    	* 重要提示如下:
//	    	* HttpUtils请从
//	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
//	    	* 下载
//	    	*
//	    	* 相应的依赖请参照
//	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
//	    	*/
//	    	org.apache.http.HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
//	    	//System.out.println(response.toString());如不输出json, 请打开这行代码，打印调试头部状态码。
//                //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
//	    	//获取response的body
//	    	System.out.println(EntityUtils.toString(response.getEntity()));
//	    } catch (Exception e) {
//	    	e.printStackTrace();
//	    }
		
		ResourceBundle rb = ResourceBundle.getBundle("config/a");  
		  
		System.out.println(rb.getString(""));
	}
}
