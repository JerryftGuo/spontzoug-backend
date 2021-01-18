package com.spontzoug.server.service;


import com.spontzoug.server.model.Assistant;
import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IUserService {
    void create(User b);
    Mono<User> findById(final String id);
    Mono<User> update(User service);
    Mono<Void> deleteById(final String id);
    Mono<User> findByUsername(final String username);
    Flux<User> findAllInc(final Date date);
    Flux<User> findAll();
}