package com.proyecto.proyecto.services;

import com.proyecto.proyecto.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.Instant;
import java.util.Date;

@Service("ServicioMails")
public class ServicioMails implements IMails{
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private TemplateEngine htmlTemplateEngine;

    @Override
    public void sendEmailSimple(SimpleMailMessage email) {
        mailSender.send(email);
    }

    @Override
    @Async
    public void sendEmailHtmlAsync(Context context, String subject, String from, String to, String pathTemplate) throws MessagingException {
        sendEmailHtmlSync(context, subject, from, to, pathTemplate);
    }

    @Override
    public void sendEmailHtmlSync(Context context, String subject, String from, String to, String pathTemplate) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject(subject);
        message.setFrom(from);
        message.setTo(InternetAddress.parse(to));

        String htmlContent = htmlTemplateEngine.process(pathTemplate, context);
        message.setText(htmlContent, true);

        mailSender.send(mimeMessage);
    }


}
