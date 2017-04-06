package com.java.xdd.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FileUtil {
    private FileUtil(){}

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 文件下载
     * @param file 用户下载的文件
     * @param request
     * @param response
     * @throws Exception
     */
    public static void exportExcel(File file, HttpServletRequest request, HttpServletResponse response) throws Exception{
        InputStream inputStream = new FileInputStream(file);

        /** 解决不同浏览器的乱码问题 */
        String encoding = "utf-8";
        /** 获取浏览器的类型 */
        String userAgent = request.getHeader("user-agent");
        /** 判断是否是IE浏览器 */
        if(userAgent.toLowerCase().contains("msie")){
            encoding = "gbk";
        }
        String fileName = file.getName();
        /** 编码 */
        fileName = new String(fileName.getBytes(encoding),"iso8859-1");
        /** 设置响应头，让浏览器使用附件下载形式下载该文件 */
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write("".getBytes());

        byte[] buf = new byte[1024 * 8];
        int len;
        while ((len = inputStream.read(buf)) != -1) {
            outputStream.write(buf, 0, len);
        }

        outputStream.close();
        inputStream.close();
    }
}
