package com.weiit.resource.common.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.weiit.resource.common.config.WeiitSMSConfig;

/**
 * Weiit团队手机短信服务工具类,目前支持腾讯云(Qcloud)、阿里云(aliyun)短信服务商
 * @author：半个鼠标
 * @date：2018年3月22日
 * @version 1.0
 * @company http://www.wei-it.com 微邦互联
 */
public class WeiitSMSUtil {
	
	
	
	/**
	 * 短信接口方式一：腾讯云短信服务，最多支持200个
	 * @param mobileNums表示手机号码，可以是多个，多个用逗号分开，比如“15622720546,18565660736”
	 * @param templateId表示短信模板id，模板中存在{1}，{2}这样的占位符
	 * @param contents 表示需要替换的内容，是一个字符串数组，每个字符串数组按照顺利会替换模板中的占位符{1}，{2}
	 * @return
	 */
	public static boolean sendMobileMessageByQcloud(String mobileNums,String templateId,String [] contents){
		try {
			String [] phones=mobileNums.split(",");
			if(phones.length<2){//单发短信
				SmsSingleSender  ssender = new SmsSingleSender (Integer.parseInt(WeiitSMSConfig.getSmsQcloudAppId()), WeiitSMSConfig.getSmsQcloudAppKey());
				SmsSingleSenderResult result = ssender.sendWithParam("86",phones[0],Integer.parseInt(templateId), contents, WeiitSMSConfig.getSmsQcloudSign(), "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
			    return result.result==0?true:false;
			}else{
				SmsMultiSender  ssender = new SmsMultiSender (Integer.parseInt(WeiitSMSConfig.getSmsQcloudAppId()), WeiitSMSConfig.getSmsQcloudAppKey());
				SmsMultiSenderResult result = ssender.sendWithParam("86",phones,Integer.parseInt(templateId), contents, WeiitSMSConfig.getSmsQcloudSign(), "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
			    return result.result==0?true:false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	/**短信接口方式二：阿里云短信服务
	 * @param mobileNums表示手机号码，可以是多个，多个用逗号分开，比如“15622720546,18565660736”
	 * @param templateId 表示短信模板id，模板中存在{1}，{2}这样的占位符
	 * @param contents 表示一个json的字符串,比如：{\"code\":\"123\"}
	 */
	public static boolean sendMobileMessageByAliyun(String mobileNums,String templateId,String contents){
		try {
			 //可自助调整超时时间
	        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
	        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

	        //初始化acsClient,暂不支持region化
	        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", WeiitSMSConfig.getSmsAliAccessKeyId(), WeiitSMSConfig.getSmsAliAccessKeySecret());
	        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
	        IAcsClient acsClient = new DefaultAcsClient(profile);

	        //组装请求对象-具体描述见控制台-文档部分内容
	        SendSmsRequest request = new SendSmsRequest();
	        //必填:待发送手机号
	        request.setPhoneNumbers(mobileNums);
	        //必填:短信签名-可在短信控制台中找到
	        request.setSignName(WeiitSMSConfig.getSmsAliSign());
	        //必填:短信模板-可在短信控制台中找到
	        request.setTemplateCode(templateId);
	        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
	        //request.setTemplateParam("{\"code\":\"123\"}");
	        request.setTemplateParam(contents);
	        //hint 此处可能会抛出异常，注意catch
	        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request); 
	        if(sendSmsResponse.getCode().equals("OK")){
	        	return true;
	        }else{
	        	return false;
	        }
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//测试
	public static void main(String[] args) throws Exception, HTTPException, IOException {
		
		
		//腾讯云短信调用测试，模板内容：您的验证码为{1}，请于{2}分钟内正确输入，如非本人操作，请忽略此短信。
		String [] content={"546545"};
		boolean isSend=WeiitSMSUtil.sendMobileMessageByQcloud("18565660736", "260699",content);
		//System.out.println(isSend);
		
		
		//阿里云短信调用测试,模板内容：验证码${code}，您正在进行身份验证，打死不要告诉别人哦！
		//String [] content3={"{\"code\":\"123\"}"};
		//boolean isSend3=WeiitSMSUtil.sendMobileMessageByAliyun("18565660736", "SMS_152465363",content3[0]);
		
		//System.out.println(isSend3);		
		
	}
}
