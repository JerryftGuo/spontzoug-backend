package com.spontzoug.server.service;

import com.spontzoug.server.model.ResidentialService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IResidentialServiceService {
    void create(ResidentialService b);
    Mono<ResidentialService> findById(final String id);
    Mono<ResidentialService> update(ResidentialService service);
    Mono<Void> deleteById(final String id);
    Flux<ResidentialService> findByBusinessid(final String id);
    Flux<ResidentialService> findByBusinessidInc(final String id, final Date date);
    Flux<ResidentialService> findDynamics(final String id);
    Flux<ResidentialService> findDynamicsInc(final String id, final Date date);
    Flux<ResidentialService> findByRegion(final String id);
    Flux<ResidentialService> findByRegionInc(final String id, final Date date);
    Mono<Long> countActive(final String id);
}