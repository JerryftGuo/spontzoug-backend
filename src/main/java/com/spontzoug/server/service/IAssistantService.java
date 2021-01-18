package com.spontzoug.server.service;

import com.spontzoug.server.model.Assistant;
import com.spontzoug.server.model.Assistant;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAssistantService {
    void create(Assistant b);
    Mono<Assistant> findById(final String id);
    Mono<Assistant> update(Assistant receptionist);
    Mono<Void> deleteById(final String id);
    Flux<Assistant> findByBusinessid(final String id);
    Flux<Assistant> findByBusinessidInc(final String id, final Date date);
    Flux<Assistant> findDynamics(final String id);
    Flux<Assistant> findDynamicsInc(final String id, final Date date);
}