package com.spontzoug.server.service;

import com.spontzoug.server.repository.IBusinessRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EmailService implements IEmailService {
    @Override
    public Mono<Boolean> sendVerificationEmail(String recipient, String code){
        return Mono.just(true);
    }
    @Override
    public Mono<Boolean> sendNotificationEmail(String recipient, String notification){
        return Mono.just(true);
    }

}
