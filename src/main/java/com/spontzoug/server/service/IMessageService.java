package com.spontzoug.server.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

public interface IMessageService {
    Mono<Boolean>  sendEmail(final  String recipient, final String content);
    Mono<Boolean> sendTextMessage(final String recipient, final String content);
}