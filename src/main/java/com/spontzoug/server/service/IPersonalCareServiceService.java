package com.spontzoug.server.service;

import com.spontzoug.server.model.PersonalCareService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IPersonalCareServiceService {
    void create(PersonalCareService b);
    Mono<PersonalCareService> findById(final String id);
    Mono<PersonalCareService> update(PersonalCareService service);
    Mono<Void> deleteById(final String id);
    Flux<PersonalCareService> findByBusinessid(final String id);
    Flux<PersonalCareService> findByBusinessidInc(final String id, final Date date);
    Flux<PersonalCareService> findDynamics(final String id);
    Flux<PersonalCareService> findDynamicsInc(final String id, final Date date);
    Flux<PersonalCareService> findByRegion(final String id);
    Flux<PersonalCareService> findByRegionInc(final String id, final Date date);
    Mono<Long> countActive(final String id);
}