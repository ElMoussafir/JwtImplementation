package com.example.jwtimplementation.api.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String body, String subjet) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(subjet);
        simpleMailMessage.setText(body);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom("noreply@vdab.be");
        this.javaMailSender.send(simpleMailMessage);
        System.out.println("Email has been send");
    }
}
