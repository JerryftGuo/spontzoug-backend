package com.spontzoug.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Slf4j
@Service
public class MessageService implements  IMessageService{
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Mono<Boolean> sendEmail(final String recipient, final String content){
        log.info("code:"+content);

        /*
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(recipient);
        msg.setSubject("test message");
        msg.setText("holle world message");
        mailSender.send(msg);
        */
        return Mono.just(true);
    }

    @Override
    public Mono<Boolean> sendTextMessage(final String recipient, final String content){
        return Mono.just(true);
    }
}