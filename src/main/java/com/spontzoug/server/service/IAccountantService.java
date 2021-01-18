package com.spontzoug.server.service;

import com.spontzoug.server.model.Accountant;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAccountantService {
    void create(Accountant b);
    Mono<Accountant> findById(final String id);
    Mono<Accountant> update(Accountant receptionist);
    Mono<Void> deleteById(final String id);
    Flux<Accountant> findByBusinessid(final String id);
    Flux<Accountant> findByBusinessidInc(final String id, final Date date);
}