package com.java.xdd.opencv;


import org.bytedeco.javacv.FFmpegFrameGrabber;

import javax.imageio.ImageIO;
import java.io.File;

public class ShiPingTest {
    public static void main(String[] args){

    }

    public static void test1() throws Exception{
        FFmpegFrameGrabber g = new FFmpegFrameGrabber("textures/video/anim.mp4");
        g.start();

        for (int i = 0 ; i < 50 ; i++) {
            //ImageIO.write(g.grab().getBufferedImage(), "png", new File("frame-dump/video-frame-" + System.currentTimeMillis() + ".png"));
        }

        g.stop();
    }
}
