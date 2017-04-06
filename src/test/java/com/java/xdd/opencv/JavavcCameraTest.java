package com.java.xdd.opencv;


import org.bytedeco.javacpp.indexer.Indexer;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import javax.swing.*;
import java.awt.*;

/**
 * 调用本地摄像头窗口视频
 *
 * @author eguid
 * @version 2016年6月13日
 * @see
 * @since javacv1.2
 */

public class JavavcCameraTest {
    public static void main(String[] args) throws Exception {
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.start();   //开始获取摄像头数据
        CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
        canvas.setBounds(100,100,500,500); //设置框大小
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setAlwaysOnTop(true);


        while (true) {
            if (!canvas.isDisplayable()) {//窗口是否关闭
                //grabber.stop();//停止抓取
                System.exit(2);//退出
            }
            canvas.showImage(grabber.grab());//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像

            Graphics2D bGraphics = canvas.createGraphics();
            Frame frame = grabber.grab();

            //bGraphics.drawImage(frame.getBufferedImage(),null,0,0);

            Thread.sleep(50);//50毫秒刷新一次图像
        }

    }




}  