package com.spontzoug.server.service;

import com.spontzoug.server.model.HealthService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IHealthServiceService {
    void create(HealthService b);
    Mono<HealthService> findById(final String id);
    Mono<HealthService> update(HealthService service);
    Mono<Void> deleteById(final String id);
    Flux<HealthService> findByBusinessid(final String id);
    Flux<HealthService> findByBusinessidInc(final String id, final Date date);
    Flux<HealthService> findDynamics(final String id);
    Flux<HealthService> findDynamicsInc(final String id, final Date date);
    Flux<HealthService> findByRegion(final String id);
    Flux<HealthService> findByRegionInc(final String id, final Date date);
    Mono<Long> countActive(final String id);
}