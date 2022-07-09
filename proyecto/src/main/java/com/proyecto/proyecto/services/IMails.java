package com.proyecto.proyecto.services;

import com.proyecto.proyecto.entities.Usuario;
import org.springframework.mail.SimpleMailMessage;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

public interface IMails {
    void sendEmailSimple(SimpleMailMessage email);
    void sendEmailHtmlAsync(Context context, String subject, String from, String to, String pathTemplate) throws MessagingException;
    void sendEmailHtmlSync(Context context, String subject, String from, String to, String pathTemplate) throws MessagingException;

}
