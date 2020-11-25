package com.weiit.resource.common.entity;


import java.awt.image.BufferedImage;
import jp.sourceforge.qrcode.data.QRCodeImage;

public class QRCodeEntity implements QRCodeImage {

    BufferedImage bufImg;  
    
    public QRCodeEntity(BufferedImage bufImg) {  
        this.bufImg = bufImg;  
    }  
    public int getHeight() {  
        return bufImg.getHeight();  
    }  
    public int getPixel(int x, int y) {  
        return bufImg.getRGB(x, y);  
    }  
    public int getWidth() {  
        return bufImg.getWidth();  
    }  
}