package com.spontzoug.server.service;

import com.spontzoug.server.model.Practitioner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IPractitionerService {
    void create(Practitioner b) ;
    Mono<Practitioner> findById(final String id);
    Mono<Practitioner> update(Practitioner practitioner);
    Mono<Void> deleteById(final String id);
    Flux<Practitioner> findByBusinessid(final String id);
    Flux<Practitioner> findByBusinessidInc(final String id, final Date date);
    Flux<Practitioner> findDynamics(final String id);
    Flux<Practitioner> findDynamicsInc(final String id, final Date date);
}