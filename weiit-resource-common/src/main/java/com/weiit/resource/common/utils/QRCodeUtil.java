package com.weiit.resource.common.utils;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;
import com.weiit.resource.common.entity.QRCodeEntity;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

public class QRCodeUtil { 
    /**
     * 解析二维码(QRCode) 
     * @param imgPath
     * @return
     */
    public static  String decoderQRCode(String imgPath) {  
        // QRCode 二维码图片的文件  
        File imageFile = new File(imgPath);  
        BufferedImage bufImg = null;   
        String content = null;  
        try {  
            bufImg = ImageIO.read(imageFile);  
            QRCodeDecoder decoder = new QRCodeDecoder();  
            content = new String(decoder.decode(new QRCodeEntity(bufImg)), "utf-8");   
        } catch (IOException e) {  
            System.out.println("Error: " + e.getMessage());  
            e.printStackTrace();  
        } catch (DecodingFailedException dfe) {  
            System.out.println("Error: " + dfe.getMessage());  
            dfe.printStackTrace();  
        }  
        return content;  
    }  
    /**  
     * 生成二维码(QRCode)图片  
     * @param content 存储内容  
     * @param imgPath 图片路径  
     * @param imgType 图片类型  
     */    
    public static void encoderQRCode(String content, String imgPath, String imgType) {    
        encoderQRCode(content, imgPath, imgType, 7);    
    }  
    /**  
     * 生成二维码(QRCode)图片  
     * @param content 存储内容  
     * @param imgPath 图片路径  
     * @param imgType 图片类型  
     * @param size 二维码尺寸  
     */    
    public static void encoderQRCode(String content, String imgPath, String imgType, int size) {    
        try {    
            BufferedImage bufImg = qRCodeCommon(content, imgType, size);    
                
            File imgFile = new File(imgPath);    
            // 生成二维码QRCode图片    
            ImageIO.write(bufImg, imgType, imgFile);   
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
    }  
      
    /**  
     * 生成二维码(QRCode)图片的公共方法  
     * @param content 存储内容  
     * @param imgType 图片类型  
     * @param size 二维码尺寸  
     * @return  
     */    
    private static BufferedImage qRCodeCommon(String content, String imgType, int size) {    
        BufferedImage bufImg = null;    
        try {    
            Qrcode qrcodeHandler = new Qrcode();    
            // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小    
            qrcodeHandler.setQrcodeErrorCorrect('M');    
            qrcodeHandler.setQrcodeEncodeMode('B');    
            // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大    
            qrcodeHandler.setQrcodeVersion(size);    
            // 获得内容的字节数组，设置编码格式    
            byte[] contentBytes = content.getBytes("utf-8");    
            // 图片尺寸    
            int imgSize = 67 + 12 * (size - 1);    
            bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);    
            Graphics2D gs = bufImg.createGraphics();    
            // 设置背景颜色    
            gs.setBackground(Color.WHITE);    
            gs.clearRect(0, 0, imgSize, imgSize);    
    
            // 设定图像颜色> BLACK    
            gs.setColor(Color.BLACK);    
            // 设置偏移量，不设置可能导致解析出错    
            int pixoff = 2;    
            // 输出内容> 二维码    
            if (contentBytes.length > 0 && contentBytes.length < 800) {    
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);    
                for (int i = 0; i < codeOut.length; i++) {    
                    for (int j = 0; j < codeOut.length; j++) {    
                        if (codeOut[j][i]) {    
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);    
                        }    
                    }    
                }    
            } else {    
                throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");    
            }    
            gs.dispose();    
            bufImg.flush();    
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
        return bufImg;    
    }  
    
    /**
	 * 创建一个二维码,并且上传到腾讯万象优图
	 * 
	 * @param content
	 *            二维码内容
	 * @param logoUrl
	 *            二维码logo图标地址
	 * @return 二维码地址
	 */
	public String createQRCodeAndUploadQcloud(String content, String logoUrl) {
		String pictureName = "";
		try {

			Qrcode qrcodeHandler = new Qrcode();
			// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
			qrcodeHandler.setQrcodeErrorCorrect('M');
			// N代表数字,A代表字符a-Z,B代表其他字符
			qrcodeHandler.setQrcodeEncodeMode('B');
			// 设置设置二维码版本，取值范围1-40，值越大尺寸越大，可存储的信息越大
			qrcodeHandler.setQrcodeVersion(8);

			byte[] contentBytes = content.getBytes("utf-8");
			BufferedImage bufImg = new BufferedImage(149, 149,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();

			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, 149, 149);

			// 设定图像颜色 > BLACK
			gs.setColor(Color.BLACK);

			// 设置偏移量 不设置可能导致解析出错
			int pixoff = 2;
			// 输出内容 > 二维码
			if (contentBytes.length > 0 && contentBytes.length < 150) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				System.err.println("QRCode content bytes length = "
						+ contentBytes.length + " not in [ 0,125]. ");
				return "";
			}
			if(logoUrl!=null&&!logoUrl.equals("")){//如果传递了图片地址过来是正确的，则将logo图片贴在二维码中间
				URL imgUrl = new URL(logoUrl);
				HttpURLConnection httpUrl = (HttpURLConnection) imgUrl
						.openConnection();
				httpUrl.connect();
				InputStream in = httpUrl.getInputStream();
				Image img = ImageIO.read(in);// 实例化一个Image对象。
				gs.drawImage(img, 60, 60, null);
			}
			
			gs.dispose();
			bufImg.flush();

			// 生成二维码QRCode图片
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bufImg, "png", baos);
			byte[] data = baos.toByteArray();
			pictureName = WeiitUtil.uploadFile(data, "jpg");

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		return pictureName;
	}

}
