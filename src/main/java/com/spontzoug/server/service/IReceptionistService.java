package com.spontzoug.server.service;

import com.spontzoug.server.model.Receptionist;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IReceptionistService {
    void create(Receptionist b);
    Mono<Receptionist> findById(final String id);
    Mono<Receptionist> update(Receptionist receptionist);
    Mono<Void> deleteById(final String id);
    Flux<Receptionist> findByBusinessid(final String id);
    Flux<Receptionist> findByBusinessidInc(final String id, final Date date);
    Flux<Receptionist> findDynamics(final String id);
    Flux<Receptionist> findDynamicsInc(final String id, final Date date);
}