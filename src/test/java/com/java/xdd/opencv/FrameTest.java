package com.java.xdd.opencv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameTest {

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("这是一个框口");
        frame.setVisible(true); //显示框口
        frame.setBounds(100, 100, 500, 500); //设置框大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);


        /** 获取该图片2D画笔 */
        Graphics2D g = (Graphics2D) frame.getGraphics(); //获取画笔
        /** 设置画笔粗细 */
        g.setStroke(new BasicStroke(3.0f));
        /** 设置画笔颜色 */
        g.setColor(Color.RED);
        /** 消除锯齿 */
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        /** 让活动的节点高亮显示(绘制一个圆角矩形) */
        g.drawRoundRect(110, //开始位置的X坐标
                110, //开始位置的Y坐标
                100,  //圆角矩形的宽
                100, //圆角矩形的高
                10, 10); //圆角的大小


        JButton button = new JButton("登录");//button按钮
        button.setBounds(200, 200, 65, 20);//按钮大小
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String str=e.getActionCommand();
                if("登录".equals(str)){
                    String getName ="登录";
                    JOptionPane.showConfirmDialog(null, "您输入的用户名是"+getName);
                }

            }
        });
        //将按钮添加窗口
        frame.add(button);



        while (true) {
            if (!frame.isDisplayable()) {//窗口是否关闭
                System.exit(2);//退出
            }
            Thread.sleep(50);//50毫秒刷新一次图像
        }
    }
}
