package com.spontzoug.server.service;

import com.spontzoug.server.model.AutomotiveSaleCate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomotiveSaleCateService {
    void create(AutomotiveSaleCate m);
    Mono<AutomotiveSaleCate> findById(final String id);
    Mono<AutomotiveSaleCate> update(AutomotiveSaleCate m);
    Mono<Void> deleteById(final String id);
    Flux<AutomotiveSaleCate> findByBusinessid(final String id);
    Flux<AutomotiveSaleCate> findByBusinessidInc(final String id, final Date date);
    Flux<AutomotiveSaleCate> findDynamics(final String id);
    Flux<AutomotiveSaleCate> findDynamicsInc(final String id, final Date date);
    Flux<AutomotiveSaleCate> findByRegion(final String region);
    Flux<AutomotiveSaleCate> findByRegionInc(final String region, final Date date);
}