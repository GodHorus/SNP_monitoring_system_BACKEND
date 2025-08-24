package com.example.master.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // These values come from application.properties
        mailSender.setHost(System.getProperty("spring.mail.host", "smtp.gmail.com"));
        mailSender.setPort(Integer.parseInt(System.getProperty("spring.mail.port", "587")));
        mailSender.setUsername(System.getProperty("spring.mail.username"));
        mailSender.setPassword(System.getProperty("spring.mail.password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");

        return mailSender;
    }
}
