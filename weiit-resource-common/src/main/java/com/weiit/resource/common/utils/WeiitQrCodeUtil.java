package com.weiit.resource.common.utils;


import cn.hutool.extra.qrcode.QrCodeUtil;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class WeiitQrCodeUtil {
    /**
     * 解析二维码(QRCode)
     * @param imgPath
     * @return
     */
    public static  String decoderQRCode(String imgPath) {
        // QRCode 二维码图片的文件
        File imageFile = new File(imgPath);
        String content = QrCodeUtil.decode(imageFile);
        return content;
    }
    /**
     * 生成二维码(QRCode)图片
     * @param content 存储内容
     * @param imgPath 图片路径
     */
    public static void encoderQRCode(String content, String imgPath) {
        try {
            QrCodeUtil.generate(content,300,300,new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 生成二维码(QRCode)图片的公共方法
     * @param content 存储内容
     * @return
     */
    private static BufferedImage qRCodeCommon(String content) {
        BufferedImage bufImg = QrCodeUtil.generate(content,300,300);
        return bufImg;
    }

    /**
     * 创建一个二维码,并且上传到腾讯万象优图
     *
     * @param content
     *            二维码内容
     * @return 二维码地址
     */
    public String createQRCodeAndUploadQcloud(String content)  {
        try {
            return WeiitUtil.uploadFile(QrCodeUtil.generatePng(content,300,300),"png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
