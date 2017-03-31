package com.java.xdd.common.controller;

import com.java.xdd.common.domain.Plupload;
import com.java.xdd.common.service.PluploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RequestMapping("file")
@Controller
@CrossOrigin
public class FileController {
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
}
