/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.services;

import com.usermanagement.UserManagement.maildto.MailResponse;
import com.usermanagement.UserManagement.maildto.MailRequest;
import freemarker.template.Template;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import javax.mail.MessagingException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 *
 * @author Reza
 */
@Service
public class EmailService {

    @Autowired
    JavaMailSender sender;

    @Autowired
    Configuration config;

    public MailResponse sendEmail(MailRequest reques, Map<String, Object> model) {
        MailResponse response = new MailResponse();
        MimeMessage message = sender.createMimeMessage();
        try {
            //set Media Type
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            //add Attachment
//            helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

            Template template = config.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            helper.setTo(reques.getTo());
            helper.setText(html, true);
            helper.setSubject(reques.getSubject());
            helper.setFrom(reques.getFrom());
            sender.send(message);

            response.setMessage("Mail send to : " + reques.getTo());
            response.setStatus(true);
        } catch (MessagingException | IOException | TemplateException e) {
            response.setMessage("Mail Sending failure : " + e.getMessage());
            response.setStatus(Boolean.FALSE);
        }
        return response;
    }
}
