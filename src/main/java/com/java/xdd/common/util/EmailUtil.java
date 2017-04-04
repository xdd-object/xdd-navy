package com.java.xdd.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public class EmailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    private final String from = "fkjava8888@163.com";

    /**
     * 发送邮件方法
     * @param to 收件人
     * @param subject 邮件的主题
     * @param msg 邮件消息体
     * @param html 是否为html格式的邮件: true : html格式   false: 文本格式
     * @return true : 发送成功   false: 发送失败
     */
    public boolean send(String to, String subject, String msg, boolean html){
        try{
            /** 创建邮件消息体对象 */
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            /** 创建邮件消息体帮助对象 */
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            /** 设置邮件收件人 */
            helper.setTo(to);
            /** 设置邮件发送人 */
            helper.setFrom(from);
            /** 设置邮件的主题 */
            helper.setSubject(subject);
            /** 设置邮件中的内容 */
            helper.setText(msg, html);
            /** 发送邮件 */
            javaMailSender.send(mimeMessage);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
