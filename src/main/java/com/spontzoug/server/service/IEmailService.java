package com.spontzoug.server.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

public interface IEmailService {
    Mono<Boolean> sendVerificationEmail(String recipient, String code);
    Mono<Boolean> sendNotificationEmail(String recipient, String notification);
}