package com.spontzoug.server.service;

import com.spontzoug.server.model.RealEstateService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IRealEstateServiceService {
    void create(RealEstateService b);
    Mono<RealEstateService> findById(final String id);
    Mono<RealEstateService> update(RealEstateService service);
    Mono<Void> deleteById(final String id);
    Flux<RealEstateService> findByBusinessid(final String id);
    Flux<RealEstateService> findByBusinessidInc(final String id, final Date date);
    Flux<RealEstateService> findDynamics(final String id);
    Flux<RealEstateService> findDynamicsInc(final String id, final Date date);
    Flux<RealEstateService> findByRegion(final String id);
    Flux<RealEstateService> findByRegionInc(final String id, final Date date);
    Mono<Long> countActive(final String id);
}