package com.ke.web.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author ke
 * @ClassName ImageUtil
 * @Description 图像工具
 * @Date 2019/11/2
 * @Version 1.0
 **/
public class ImageUtil {

    public static BufferedImage getImage(String content, int width, int heigth) {
        BufferedImage img = new BufferedImage(width, heigth, 1);
        Graphics2D g = (Graphics2D) img.getGraphics();
        Color foreColor = new Color(64, 160, 62);
        Color bg = new Color(244,244,244);
        g.setColor(bg);
        g.fillRect(0,0, 200, 100);
        g.setPaint(foreColor);
        Font font = new Font("微软雅黑", Font.BOLD, 45);
        g.setFont(font);
        g.drawString(content, width/4, heigth/2);
        g.rotate(1.5);
        return img;
    }

//    public static void main(String[] args) throws IOException {
//        String code = VerificationUtil.getCode();
//        BufferedImage img = ImageUtil.getImage(code, 200, 100);
//        File file = new File("F:/code.jpg");
//        ImageIO.write(img, "jpg", file); //写入文件夹
//        System.out.println("成功");
//    }

}
