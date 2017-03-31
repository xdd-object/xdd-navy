package com.java.xdd.test;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * 头像识别
 */
public class Test2 {

    /*@Test
    public void findFaces() throws Exception {
        String fileName1 = "G:\\xdd\\aa\\file\\1.jpg";
        String fileName2 = "G:\\xdd\\aa\\file\\2.jpg";
        String fileName3 = "G:\\xdd\\aa\\file\\3.jpg";
        String fileName4 = "G:\\xdd\\aa\\file\\4.jpg";
        String fileName5 = "G:\\xdd\\aa\\file\\5.jpg";
        Detector detector = Detector.create("G:\\xdd\\aa\\file\\haarcascade_frontalface_default.xml");
        BufferedImage bi1 = ImageIO.read(new File(fileName1));

        BufferedImage bi2 = ImageIO.read(new File(fileName2));
        BufferedImage bi3 = ImageIO.read(new File(fileName3));
        BufferedImage bi4 = ImageIO.read(new File(fileName4));
        BufferedImage bi5 = ImageIO.read(new File(fileName5));


        List<Rectangle> res = detector.getFaces(bi1, 2, 1.25f, 0.1f, 3, true);
        if(res.size() > 0) aa(res,bi1,1);

        System.out.println(res);
        res = detector.getFaces(bi2, 2, 1.25f, 0.1f, 3, true);
        System.out.println(res);
        res = detector.getFaces(bi3, 2, 1.25f, 0.1f, 3, true);
        System.out.println(res);

        res = detector.getFaces(bi4, 2, 1.25f, 0.1f, 3, true);
        System.out.println(res);
        if(res.size() > 0) aa(res,bi4,4);

        res = detector.getFaces(bi5, 2, 1.25f, 0.1f, 3, true);
        System.out.println(res);
        if(res.size() > 0) aa(res,bi5,5);


    }

    //画图
    public void aa(List<Rectangle> res,BufferedImage bi1,int name) throws Exception{
        *//** 获取该图片2D画笔 *//*
        Graphics2D g = (Graphics2D) bi1.getGraphics();
        *//** 设置画笔粗细 *//*
        g.setStroke(new BasicStroke(1.0f));
        *//** 设置画笔颜色 *//*
        g.setColor(Color.RED);
        *//** 消除锯齿 *//*
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        *//** 让活动的节点高亮显示(绘制一个圆角矩形) *//*
        for (Rectangle graphicInfo : res) {
            g.drawRoundRect((int) graphicInfo.getX(), //开始位置的X坐标
                    (int) graphicInfo.getY(), //开始位置的Y坐标
                    (int) graphicInfo.getWidth(),  //圆角矩形的宽
                    (int) graphicInfo.getHeight(), //圆角矩形的高
                    10, 10); //圆角的大小
        }
        ImageIO.write(bi1, "png", new FileOutputStream("G://" + name + ".jpg"));
    }


    @Test
    public void findFaces2() throws Exception {
        String fileName5 = "G:\\xdd\\aa\\file\\4.jpg";
        BufferedImage bi5 = ImageIO.read(new File(fileName5));

        Detector detector = Detector.create("G:\\xdd\\aa\\file\\haarcascade_frontalface_default.xml");
        //当一张图片人数较多，参数这样设置效果最佳(min_neighbors) 该数据是参考测试类所写
        List<Rectangle> res = detector.getFaces(bi5, 1, 1.25f, 0.1f, 1, true);
        System.out.println(res);
        if(res.size() > 0) aa(res,bi5,5);


    }*/


}
