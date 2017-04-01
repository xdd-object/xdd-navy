package com.java.xdd.shiro.controller;

import com.java.xdd.shiro.domain.User;
import com.java.xdd.shiro.exception.CustomException;
import com.java.xdd.shiro.service.UserService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 控制登录
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录，如果是get方式会跳转到登录页面，post方式是提交登录数据
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request) throws Exception{

        String uri = request.getRequestURI();//返回请求行中的资源名称
        String url = request.getRequestURL().toString();//获得客户端发送请求的完整url
        String ip = request.getRemoteAddr();//返回发出请求的IP地址
        String params = request.getQueryString();//返回请求行中的参数部分
        String host=request.getRemoteHost();//返回发出请求的客户机的主机名
        int port =request.getRemotePort();//返回发出请求的客户机的端口号。



        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        //根据shiro返回的异常类路径判断，抛出指定异常信息
        if(exceptionClassName!=null){
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                //最终会抛给异常处理器
                throw new CustomException("账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                throw new CustomException("用户名/密码错误");
            } else if("randomCodeError".equals(exceptionClassName)){
                throw new CustomException("验证码错误 ");
            }else {
                throw new Exception();//最终在异常处理器生成未知错误
            }
        }

        return "login";
    }

    /** 去注册页面 */
    @RequestMapping(value = "/toRegister")
    public String toRegister(){
        return "register";
    }

    /** 注册 */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@RequestBody User user){
        Long userId = userService.save(user);
        ModelAndView mv = new ModelAndView("aa");
        mv.addObject("userId", userId);
        return mv;
    }

    /** 退出 */
    @RequestMapping("/logout")
    public ModelAndView logout(){
        ModelAndView mv = new ModelAndView("aa");
        mv.addObject("userId", "12138");
        return mv;
    }


}
