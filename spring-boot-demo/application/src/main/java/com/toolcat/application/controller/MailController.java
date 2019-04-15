package com.toolcat.application.controller;

import com.toolcat.application.dto.model.MailVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * 发送邮件
 */
@Log4j2
@RestController
public class MailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    MailProperties mailProperties;

    @Autowired
    TemplateEngine templateEngine;

    @Async // 异步发邮件
    @PostMapping(value = "/send_mail")
    public String sendMail(MailVO mailVO) {
        Assert.notNull(mailVO, "邮件对象为空");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(mailProperties.getUsername());
            mimeMessageHelper.setTo(mailVO.getConsignee());
            mimeMessageHelper.setSubject(mailVO.getSubject());
            mimeMessageHelper.setSentDate(new Date());

            // 邮件内容
            String content = "";
            if (null == mailVO.getContent()) {
                // 设置内容模板
                Context context = new Context();
                context.setVariable("name", "你");
                context.setVariable("prize-1", "玩具车");
                context.setVariable("prize-2", "遥控车");
                content = templateEngine.process("mail", context);
            } else {
                content = mailVO.getContent();
            }
            mimeMessageHelper.setText(content, true); // 是否解析html

            // 附件
            mimeMessageHelper.setText(content);
            if (null != mailVO.getAttachment()) {
                MultipartFile attachment = mailVO.getAttachment();
                mimeMessageHelper.addAttachment(attachment.getOriginalFilename(), attachment);
            }
            javaMailSender.send(mimeMessage);
//            log.info("content:{}",content);
            return "发送成功";
        } catch (MessagingException e) {
            log.error("模板邮件发送失败->message:{}", e.getMessage());
            e.printStackTrace();
            return "发送失败,请重试";
        }
    }

}
