package com.spontzoug.server.service;

import com.spontzoug.server.model.AutomotiveServiceService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomotiveServiceServiceService {
    void create(AutomotiveServiceService b);
    Mono<AutomotiveServiceService> findById(final String id);
    Mono<AutomotiveServiceService> update(AutomotiveServiceService service);
    Mono<Void> deleteById(final String id);
    Flux<AutomotiveServiceService> findByBusinessid(final String id);
    Flux<AutomotiveServiceService> findByBusinessidInc(final String id, final Date date);
    Flux<AutomotiveServiceService> findDynamics(final String id);
    Flux<AutomotiveServiceService> findDynamicsInc(final String id, final Date date);
    Flux<AutomotiveServiceService> findByRegion(final String id);
    Flux<AutomotiveServiceService> findByRegionInc(final String id, final Date date);
    Mono<Long> countActive(final String id);
}