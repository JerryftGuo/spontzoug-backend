package com.spontzoug.server.service;

import com.spontzoug.server.model.Support;
import com.spontzoug.server.model.Support;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ISupportService {
    void create(Support b);
    Mono<Support> findById(final String id);
    Mono<Support> update(Support receptionist);
    Mono<Void> deleteById(final String id);
    Flux<Support> findByBusinessid(final String id);
    Flux<Support> findByBusinessidInc(final String id, final Date date);
    Flux<Support> findDynamics(final String id);
    Flux<Support> findDynamicsInc(final String id, final Date date);
}