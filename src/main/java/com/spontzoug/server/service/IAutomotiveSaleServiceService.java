package com.spontzoug.server.service;

import com.spontzoug.server.model.AutomotiveSaleService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomotiveSaleServiceService {
    void create(AutomotiveSaleService b);
    Mono<AutomotiveSaleService> findById(final String id);
    Mono<AutomotiveSaleService> update(AutomotiveSaleService service);
    Mono<Void> deleteById(final String id);
    Flux<AutomotiveSaleService> findByBusinessid(final String id);
    Flux<AutomotiveSaleService> findByBusinessidInc(final String id, final Date date);
    Flux<AutomotiveSaleService> findDynamics(final String id);
    Flux<AutomotiveSaleService> findDynamicsInc(final String id, final Date date);
    Flux<AutomotiveSaleService> findByRegion(final String id);
    Flux<AutomotiveSaleService> findByRegionInc(final String id, final Date date);
    Mono<Long> countActive(final String id);
}