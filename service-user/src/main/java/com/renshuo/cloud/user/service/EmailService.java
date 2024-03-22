package com.renshuo.cloud.user.service;

import com.renshuo.cloud.user.model.EmailModel;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2023/10/24.
 */
@Component
@Slf4j
public class EmailService {

    @Autowired
    private FreeMarkerConfig config;

    @Autowired
    private JavaMailSenderImpl mailSender;

    public boolean sendSimpleEmail(EmailModel email) {
        log.info("发送简单邮件信息：{}", email);
        // 实例化SimpleMailMessage
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        try {
            // 设置邮件发件人
            simpleMailMessage.setFrom(email.getSender());
            // 设置邮件接收人（可以多人）
            simpleMailMessage.setTo(email.getReceiverArray());
            // 设置邮件抄送
            String[] ccArray = email.getCcArray();
            if (ccArray.length > 0) {
                simpleMailMessage.setCc(ccArray); // 普通抄送[邮件接收人可以看到抄送给谁了]
//                simpleMailMessage.setBcc(); // 盲抄送[邮件接收人可以看不到抄送给谁了]
            }
            // 设置邮件主题
            simpleMailMessage.setSubject(email.getSubject());
            // 设置邮件内容
            Map<String,Object> data = new HashMap<>();
            data.put("userName","renshuo");
            data.put("realName","任硕");
            data.put("password","123456");
            data.put("website","www.baidu.com");
            String content = getContent("email.ftl", data);

            simpleMailMessage.setText(content);
            // 发送简单邮件
            mailSender.send(simpleMailMessage);
            return true;
        } catch (Exception e) {
            log.error(null, e);
        }
        return false;
    }
    public boolean sendMimeEmail(EmailModel email) {
        log.info("发送复杂邮件信息：{}", email);
        // 实例化SimpleMailMessage
        MimeMessage mimeMailMessage =mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage,true);
            // 设置邮件发件人
            helper.setFrom(email.getSender());
            // 设置邮件接收人（可以多人）
            helper.setTo(email.getReceiverArray());
            // 设置邮件抄送
            String[] ccArray = email.getCcArray();
            if (ccArray.length > 0) {
                helper.setCc(ccArray); // 普通抄送[邮件接收人可以看到抄送给谁了]
//                simpleMailMessage.setBcc(); // 盲抄送[邮件接收人可以看不到抄送给谁了]
            }
            // 设置邮件主题
            helper.setSubject(email.getSubject());
            // 设置邮件内容
            Map<String,Object> data = new HashMap<>();
            data.put("userName","renshuo");
            data.put("realName","任硕");
            data.put("password","123456");
            data.put("website","www.baidu.com");
            String content = getContent("email.ftl", data);

            helper.setText(content,true);
            // 发送简单邮件
            mailSender.send(mimeMailMessage);
            return true;
        } catch (Exception e) {
            log.error(null, e);
        }
        return false;
    }


    public String getContent(String templateName,Map<String,Object> map){

        try {
            Template email = config.getConfiguration().getTemplate(templateName);
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(email, map);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        return null;
    }


}
