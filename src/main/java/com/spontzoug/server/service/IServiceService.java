package com.spontzoug.server.service;

import com.spontzoug.server.model.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IServiceService {
    void create(Service b);
    Mono<Service> findById(final String id);
    Mono<Service> update(Service service);
    Mono<Void> deleteById(final String id);
    Flux<Service> findByBusinessid(final String id);
    Flux<Service> findByBusinessidInc(final String id, final Date date);
    Flux<Service> findDynamics(final String id);
    Flux<Service> findDynamicsInc(final String id, final Date date);
}