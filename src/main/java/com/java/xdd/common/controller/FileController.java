package com.java.xdd.common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.xdd.common.domain.Plupload;
import com.java.xdd.common.service.PluploadService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RequestMapping("file")
@Controller
@CrossOrigin
public class FileController {

    private final ObjectMapper mapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(FileController.class);
    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[] { ".bmp", ".jpg", ".jpeg", ".gif", ".png" };

    /**Plupload文件上传处理方法*/
    @RequestMapping(value="/pluploadUpload")
    @ResponseBody
    public void upload(Plupload plupload, HttpServletRequest request, HttpServletResponse response) {

        String FileDir = "pluploadDir";//文件保存的文件夹
        plupload.setRequest(request);//手动传入Plupload对象HttpServletRequest属性

        //int userId = ((User)request.getSession().getAttribute("user")).getUserId();
        int userId = 1000;

        //文件存储绝对路径,会是一个文件夹，项目相应Servlet容器下的"pluploadDir"文件夹，还会以用户唯一id作划分
        File dir = new File(request.getSession().getServletContext().getRealPath("/") + FileDir+"/"+userId);
        if(!dir.exists()){
            dir.mkdirs();//可创建多级目录，而mkdir()只能创建一级目录
        }
        //开始上传文件
        PluploadService.upload(plupload, dir);
    }
    /**Plupload文件上传处理方法*/
    @RequestMapping(value="/test2")
    public String test2(Plupload plupload, HttpServletRequest request, HttpServletResponse response) {

        return "test2";
    }

    /**Plupload文件上传处理方法*/
    @RequestMapping(value="/fileUpload")
    public String fileUpload(Plupload plupload, HttpServletRequest request, HttpServletResponse response) {

        return "fileUpload";
    }

    /**Plupload文件上传处理方法*/
    @RequestMapping(value="/fileUpload2")
    public String fileUpload2(Plupload plupload, HttpServletRequest request, HttpServletResponse response) {

        return "fileUpload2";
    }

    /**
     * 单文件上传
     * @return
     */
    @RequestMapping(value="/uploadFile")
    @ResponseBody
    public String uploadFile(@RequestParam("file")MultipartFile file, HttpServletRequest request) {
        // 校验图片格式
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), type)) {
                isLegal = true;
                break;
            }
        }

        // 文件新路径
        String filePath = "";

        if (logger.isDebugEnabled()) {
            logger.debug("Pic file upload .[{}] to [{}] .", file.getOriginalFilename(), filePath);
        }

        File newFile = new File(filePath);

        // 写文件到磁盘
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 校验图片是否合法
        isLegal = false;
        try {
            BufferedImage image = ImageIO.read(newFile);
            if (image != null) {
                int width = image.getWidth();
                int height = image.getHeight();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "fileUpload2";
    }

    /**
     * 单文件下载
     * @return
     */
    @RequestMapping(value="/downloadFile")
    @ResponseBody
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        return "fileUpload2";
    }
}
