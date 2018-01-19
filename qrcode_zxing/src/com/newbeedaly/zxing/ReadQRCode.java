package com.newbeedaly.zxing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by newbeedaly on 2018/1/11.
 */
public class ReadQRCode {
    public static void main(String[] args) {
        MultiFormatReader formatReader = new MultiFormatReader();

        File file = new File("E:/img.png");

        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        // 定义二维码的参数:编码，纠错能力，边框
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
        Result result = null;
        try {
            result = formatReader.decode(binaryBitmap,hints);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("解析结果："+result.toString());
        System.out.println("二维码格式类型："+result.getBarcodeFormat());
        System.out.println("二维码文本内容："+result.getText());
    }
}
