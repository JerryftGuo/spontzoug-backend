package com.spontzoug.server.service;

import com.spontzoug.server.model.AutomativeServiceService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomativeServiceServiceService {
    void create(AutomativeServiceService b);
    Mono<AutomativeServiceService> findById(final String id);
    Mono<AutomativeServiceService> update(AutomativeServiceService service);
    Mono<Void> deleteById(final String id);
    Flux<AutomativeServiceService> findByBusinessid(final String id);
    Flux<AutomativeServiceService> findByBusinessidInc(final String id, final Date date);
    Flux<AutomativeServiceService> findDynamics(final String id);
    Flux<AutomativeServiceService> findDynamicsInc(final String id, final Date date);
    Flux<AutomativeServiceService> findByRegion(final String id);
    Flux<AutomativeServiceService> findByRegionInc(final String id, final Date date);
    Mono<Long> countActive(final String id);
}