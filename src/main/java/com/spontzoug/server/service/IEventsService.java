package com.spontzoug.server.service;

import com.spontzoug.server.model.Events;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IEventsService {
    void create(Events b);
    Mono<Events> findById(final String id);
    Mono<Events> update(Events service);
    Mono<Void> deleteById(final String id);
    Flux<Events> findByBusinessid(final String id);
    Flux<Events> findByBusinessidInc(final String id, final Date date);
    Flux<Events> findDynamics(final String id);
    Flux<Events> findDynamicsInc(final String id, final Date date);
    Flux<Events> findByRegion(final String id);
    Flux<Events> findByRegionInc(final String id, final Date date);
}