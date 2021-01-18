package com.spontzoug.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private String port;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String passord;
    /*
    @Value("${mail.properties.mail.smtp.startttls.enable}")
    private String starttls;
    @Value("${mail.properties.mail.smtp.auth}")
    private String auth;
    @Value("${mail.properties.mail.smtp.connectiontimeout}")
    private String connectiontimeout;
    @Value("${mail.properties.mail.smtp.timeout}")
    private String timeout;
    @Value("${mail.properties.mail.smtp.wirtetimeout}")
    private String writetimeout;

*/
    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(Integer.parseInt(port));

        mailSender.setUsername(username);
        mailSender.setPassword(passord);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.starttls.required","true");
        props.put("mail.debug","true");
        return mailSender;
    }
}