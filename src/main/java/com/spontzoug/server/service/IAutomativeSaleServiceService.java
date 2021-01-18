package com.spontzoug.server.service;

import com.spontzoug.server.model.AutomativeSaleService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomativeSaleServiceService {
    void create(AutomativeSaleService b);
    Mono<AutomativeSaleService> findById(final String id);
    Mono<AutomativeSaleService> update(AutomativeSaleService service);
    Mono<Void> deleteById(final String id);
    Flux<AutomativeSaleService> findByBusinessid(final String id);
    Flux<AutomativeSaleService> findByBusinessidInc(final String id, final Date date);
    Flux<AutomativeSaleService> findDynamics(final String id);
    Flux<AutomativeSaleService> findDynamicsInc(final String id, final Date date);
    Flux<AutomativeSaleService> findByRegion(final String id);
    Flux<AutomativeSaleService> findByRegionInc(final String id, final Date date);
    Mono<Long> countActive(final String id);
}