package com.example.jwtimplementation.api.service;

import com.example.jwtimplementation.api.auth.Email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Email email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setText(email.getText());
        simpleMailMessage.setTo(email.getTo());
        this.javaMailSender.send(simpleMailMessage);
        System.out.println("Email has been send");
    }
}
