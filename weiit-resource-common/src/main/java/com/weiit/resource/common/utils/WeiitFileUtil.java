package com.weiit.resource.common.utils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.meta.InsertOnly;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;
import com.weiit.resource.common.config.WeiitFileConfig;

import org.springframework.web.multipart.MultipartFile;

/**
 * Weiit团队图片上传存储类,目前支持腾讯云-万象优图(Qcloud)、阿里云(aliyun)、七牛云(qiniu)三家云存储服务商，再加本地存储支持
 * 
 * @author：半个鼠标(137075251@qq.com)
 * @date：2018年3月22日
 * @version 1.0
 * @company http://www.wei-it.com 微邦互联
 */
public class WeiitFileUtil {

	/**
	 * 图片存储方式一：腾讯万象优图存储服务
	 * 
	 * @param data
	 * @param fileFormat
	 *            文件后缀
	 * @return
	 */
	public String uploadFileByQcloud(byte[] data, String fileFormat) {
		// 初始化客户端配置
		ClientConfig clientConfig = new ClientConfig();
		// 设置bucket所在的区域，比如广州(gz), 天津(tj)
		clientConfig.setRegion(WeiitFileConfig.getCosRegion());
		// 初始化秘钥信息
		Credentials cred = new Credentials(Long.parseLong(WeiitFileConfig
				.getCosAppId()), WeiitFileConfig.getCosSecretId(),
				WeiitFileConfig.getCosSecretKey());
		// 初始化cosClient
		COSClient cosClient = new COSClient(clientConfig, cred);

		// 保存目录
		String cospre = WeiitFileConfig.getCosPre();
		// 图片文件夹
		String floder = DateUtil.getCurrentDate("yyyy-MM-dd");
		// 图片名称
		String fileName = UUID.randomUUID().toString() + "." + fileFormat;
		// 图片最终路径
		String cosFilePath = "/" + cospre + "/" + floder + "/" + fileName;

		UploadFileRequest overWriteFileRequest = new UploadFileRequest(
				WeiitFileConfig.getCosBucketName(), cosFilePath, data);
		overWriteFileRequest.setInsertOnly(InsertOnly.OVER_WRITE);
		String overWriteFileRet = cosClient.uploadFile(overWriteFileRequest);
		cosClient.shutdown();
		System.out.println("overwrite file ret:" + overWriteFileRet);
		return cosFilePath;

	}

	public String uploadFileByQcloud(byte[] data, String fileFormat,
			String cosFilePath) {
		// 初始化客户端配置
		ClientConfig clientConfig = new ClientConfig();
		// 设置bucket所在的区域，比如广州(gz), 天津(tj)
		clientConfig.setRegion(WeiitFileConfig.getCosRegion());
		// 初始化秘钥信息
		Credentials cred = new Credentials(Long.parseLong(WeiitFileConfig
				.getCosAppId()), WeiitFileConfig.getCosSecretId(),
				WeiitFileConfig.getCosSecretKey());
		// 初始化cosClient
		COSClient cosClient = new COSClient(clientConfig, cred);

		UploadFileRequest overWriteFileRequest = new UploadFileRequest(
				WeiitFileConfig.getCosBucketName(), cosFilePath, data);
		overWriteFileRequest.setInsertOnly(InsertOnly.OVER_WRITE);
		String overWriteFileRet = cosClient.uploadFile(overWriteFileRequest);
		cosClient.shutdown();
		System.out.println("overwrite file ret:" + overWriteFileRet);
		return cosFilePath;

	}

	/**
	 * 图片存储方式一：腾讯万象优图存储服务
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String uploadFileByQcloud(MultipartFile file) throws IOException {

		byte[] data = file.getBytes();
		String oldFileName = file.getOriginalFilename();
		String suffix = oldFileName.substring(oldFileName.lastIndexOf(".") + 1);
		// 初始化客户端配置
		ClientConfig clientConfig = new ClientConfig();
		// 设置bucket所在的区域，比如广州(gz), 天津(tj)
		clientConfig.setRegion(WeiitFileConfig.getCosRegion());
		// 初始化秘钥信息
		Credentials cred = new Credentials(Long.parseLong(WeiitFileConfig
				.getCosAppId()), WeiitFileConfig.getCosSecretId(),
				WeiitFileConfig.getCosSecretKey());
		// 初始化cosClient
		COSClient cosClient = new COSClient(clientConfig, cred);

		// 保存目录
		String cospre = WeiitFileConfig.getCosPre();
		// 图片文件夹
		String floder = DateUtil.getCurrentDate("yyyy-MM-dd");
		// 图片名称
		String fileName = UUID.randomUUID().toString() + "." + suffix;
		// 图片最终路径
		String cosFilePath = "/" + cospre + "/" + floder + "/" + fileName;

		UploadFileRequest overWriteFileRequest = new UploadFileRequest(
				WeiitFileConfig.getCosBucketName(), cosFilePath, data);
		overWriteFileRequest.setInsertOnly(InsertOnly.OVER_WRITE);
		String overWriteFileRet = cosClient.uploadFile(overWriteFileRequest);
		cosClient.shutdown();
		System.out.println("overwrite file ret:" + overWriteFileRet);
		return cosFilePath;

	}

	public String uploadFileByQcloud(MultipartFile file, String cosFilePath)
			throws IOException {

		byte[] data = file.getBytes();
		String oldFileName = file.getOriginalFilename();
		String suffix = oldFileName.substring(oldFileName.lastIndexOf(".") + 1);
		// 初始化客户端配置
		ClientConfig clientConfig = new ClientConfig();
		// 设置bucket所在的区域，比如广州(gz), 天津(tj)
		clientConfig.setRegion(WeiitFileConfig.getCosRegion());
		// 初始化秘钥信息
		Credentials cred = new Credentials(Long.parseLong(WeiitFileConfig
				.getCosAppId()), WeiitFileConfig.getCosSecretId(),
				WeiitFileConfig.getCosSecretKey());
		// 初始化cosClient
		COSClient cosClient = new COSClient(clientConfig, cred);

		UploadFileRequest overWriteFileRequest = new UploadFileRequest(
				WeiitFileConfig.getCosBucketName(), cosFilePath, data);
		overWriteFileRequest.setInsertOnly(InsertOnly.OVER_WRITE);
		String overWriteFileRet = cosClient.uploadFile(overWriteFileRequest);
		cosClient.shutdown();
		System.out.println("overwrite file ret:" + overWriteFileRet);
		return cosFilePath;

	}

	/**
	 * 图片存储方式二：阿里云图片存储服务
	 * 
	 * @param data
	 * @param fileFormat
	 *            文件后缀
	 * @return
	 */
	public String uploadFileByAliyun(MultipartFile file) throws IOException {

		byte[] data = file.getBytes();
		String oldFileName = file.getOriginalFilename();
		String suffix = oldFileName.substring(oldFileName.lastIndexOf(".") + 1);

		// 保存目录
		String cospre = WeiitFileConfig.getOssPre();
		// 图片文件夹
		String floder = DateUtil.getCurrentDate("yyyy-MM-dd");
		// 图片名称
		String fileName = UUID.randomUUID().toString() + "." + suffix;
		// 图片最终路径
		String cosFilePath = cospre + "/" + floder + "/" + fileName;

		// Endpoint以杭州为例，其它Region请按实际情况填写。
		String endpoint = WeiitFileConfig.getOssEndPoint();
		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
		// https://ram.console.aliyun.com 创建。
		String accessKeyId = WeiitFileConfig.getOssAccessKeyId();
		String accessKeySecret = WeiitFileConfig.getOssAccessKeySecret();

		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
				accessKeySecret);

		ossClient.putObject(WeiitFileConfig.getOssBucketName(), cosFilePath,
				new ByteArrayInputStream(data));

		// 关闭OSSClient。
		ossClient.shutdown();
		return cosFilePath;

	}

	public String uploadFileByAliyun(MultipartFile file, String cosFilePath)
			throws IOException {

		byte[] data = file.getBytes();
		String oldFileName = file.getOriginalFilename();
		String suffix = oldFileName.substring(oldFileName.lastIndexOf(".") + 1);

		// Endpoint以杭州为例，其它Region请按实际情况填写。
		String endpoint = WeiitFileConfig.getOssEndPoint();
		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
		// https://ram.console.aliyun.com 创建。
		String accessKeyId = WeiitFileConfig.getOssAccessKeyId();
		String accessKeySecret = WeiitFileConfig.getOssAccessKeySecret();

		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
				accessKeySecret);

		ossClient.putObject(WeiitFileConfig.getOssBucketName(), cosFilePath,
				new ByteArrayInputStream(data));

		// 关闭OSSClient。
		ossClient.shutdown();
		return cosFilePath;

	}

	/**
	 * 图片存储方式二：阿里云图片存储服务
	 * 
	 * @param data
	 * @param fileFormat
	 *            文件后缀
	 * @return
	 */
	public String uploadFileByAliyun(byte[] data, String fileFormat) {

		// Endpoint以杭州为例，其它Region请按实际情况填写。
		String endpoint = WeiitFileConfig.getOssEndPoint();
		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
		// https://ram.console.aliyun.com 创建。
		String accessKeyId = WeiitFileConfig.getOssAccessKeyId();
		String accessKeySecret = WeiitFileConfig.getOssAccessKeySecret();

		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
				accessKeySecret);

		// 保存目录
		String cospre = WeiitFileConfig.getOssPre();
		// 图片文件夹
		String floder = DateUtil.getCurrentDate("yyyy-MM-dd");
		// 图片名称
		String fileName = UUID.randomUUID().toString() + "." + fileFormat;
		// 图片最终路径
		String cosFilePath = cospre + "/" + floder + "/" + fileName;
		ossClient.putObject(WeiitFileConfig.getOssBucketName(), cosFilePath,
				new ByteArrayInputStream(data));

		// 关闭OSSClient。
		ossClient.shutdown();
		return cosFilePath;

	}

	public String uploadFileByAliyun(byte[] data, String fileFormat,
			String cosFilePath) {

		// Endpoint以杭州为例，其它Region请按实际情况填写。
		String endpoint = WeiitFileConfig.getOssEndPoint();
		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
		// https://ram.console.aliyun.com 创建。
		String accessKeyId = WeiitFileConfig.getOssAccessKeyId();
		String accessKeySecret = WeiitFileConfig.getOssAccessKeySecret();

		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
				accessKeySecret);

		ossClient.putObject(WeiitFileConfig.getOssBucketName(), cosFilePath,
				new ByteArrayInputStream(data));

		// 关闭OSSClient。
		ossClient.shutdown();
		return cosFilePath;

	}

	/**
	 * 图片存储方式四：本地存储服务
	 * 
	 * @param data
	 * @param fileFormat
	 *            文件后缀
	 * @return
	 */
	public String uploadFileByLocal(byte[] data, String fileFormat) {

		return null;

	}

	public static void main(String[] args) throws FileNotFoundException {
		// Endpoint以杭州为例，其它Region请按实际情况填写。
		String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
		// https://ram.console.aliyun.com 创建。
		String accessKeyId = "LTAIx3ruNK9IsRFB";
		String accessKeySecret = "vyLKu0pGLwVhd64PXsWWUxzSowxrEb";

		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
				accessKeySecret);

		// 上传文件流。
		InputStream inputStream = new FileInputStream("D:\\33.png");
		PutObjectResult result = ossClient.putObject("bluetax", "blue.png",
				inputStream);
		// System.out.println(result.getResponse().getUri());
		// 关闭OSSClient。
		ossClient.shutdown();
	}

}
