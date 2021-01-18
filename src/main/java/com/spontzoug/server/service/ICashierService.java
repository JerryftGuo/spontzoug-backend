package com.spontzoug.server.service;

import com.spontzoug.server.model.Cashier;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ICashierService {
    void create(Cashier b);
    Mono<Cashier> findById(final String id);
    Mono<Cashier> update(Cashier receptionist);
    Mono<Void> deleteById(final String id);
    Flux<Cashier> findByBusinessid(final String id);
    Flux<Cashier> findByBusinessidInc(final String id, final Date date);
    Flux<Cashier> findDynamics(final String id);
    Flux<Cashier> findDynamicsInc(final String id, final Date date);
}