package com.java.xdd.test;


import org.jdesktop.swingx.util.OS;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证码识别
 */
public class TestMain {

    private final String LANG_OPTION = "-l";
    private final String EOL = System.getProperty("line.separator");
    /**
     * 文件位置我防止在，项目同一路径
     */
    //private String tessPath = new File("tesseract").getAbsolutePath();
    //识别图片软件所在路径
    private String tessPath = new File("C:\\Program Files (x86)\\Tesseract-OCR\\").getAbsolutePath();

    /**
     * @param imageFile 传入的图像文件
     * @return 识别后的字符串
     */
    public String recognizeText(File imageFile) throws Exception {
        /**
         * 设置输出文件的保存的文件目录
         */
        File outputFile = new File(imageFile.getParentFile(), "output.txt");

        StringBuffer strB = new StringBuffer();
        List<String> cmd = new ArrayList<>();
        if (OS.isWindowsXP()) {
            cmd.add(tessPath + "\\tesseract");
        } else if (OS.isLinux()) {
            cmd.add("tesseract");
        } else {
            cmd.add(tessPath + "\\tesseract");
        }
        cmd.add("");
        cmd.add(outputFile.getName());
        cmd.add(LANG_OPTION); //表示输出结果文件txt名称
//      cmd.add("chi_sim"); //中文识别
        cmd.add("eng"); //eng表示用以识别的语言文件为英文。

        ProcessBuilder pb = new ProcessBuilder();
        /**
         *Sets this process builder's working directory.
         */
        pb.directory(imageFile.getParentFile());
        cmd.set(1, imageFile.getName());
        pb.command(cmd); //参数
        pb.redirectErrorStream(true);
        Process process = pb.start();
        // tesseract.exe 1.jpg 1 -l chi_sim
        // Runtime.getRuntime().exec("tesseract.exe 1.jpg 1 -l chi_sim");
        /**
         * the exit value of the process. By convention, 0 indicates normal
         * termination.
         */
//      System.out.println(cmd.toString());
        int w = process.waitFor();
        if (w == 0)// 0代表正常退出
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(outputFile.getAbsolutePath() + ".txt"),
                    "UTF-8"));
            String str;

            while ((str = in.readLine()) != null) {
                strB.append(str).append(EOL);
            }
            OutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(strB.toString().getBytes());
            in.close();
        } else {
            String msg;
            switch (w) {
                case 1:
                    msg = "Errors accessing files. There may be spaces in your image's filename.";
                    break;
                case 29:
                    msg = "Cannot recognize the image or its selected region.";
                    break;
                case 31:
                    msg = "Unsupported image format.";
                    break;
                default:
                    msg = "Errors occurred.";
            }
            throw new RuntimeException(msg);
        }
        //new File(outputFile.getAbsolutePath() + ".txt").delete();
        return strB.toString().replaceAll("\\s*", "");
    }

    @Test
    public void test1() {
        try {

            File testDataDir = new File("G:\\xdd\\aa"); //需要识别的文件所在目录
            System.out.println(testDataDir.listFiles().length);
            int i = 0;
            for (File file : testDataDir.listFiles()) {
                i++;
                String recognizeText = new TestMain().recognizeText(file);
                System.out.println(recognizeText + "\t");

                if (i % 5 == 0) {
                    System.out.println();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
