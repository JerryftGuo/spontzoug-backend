package com.spontzoug.server.service;

import com.spontzoug.server.model.AutomativeSaleCate;
import com.spontzoug.server.model.AutomativeSaleMenu;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomativeSaleCateService {
    void create(AutomativeSaleCate m);
    Mono<AutomativeSaleCate> findById(final String id);
    Mono<AutomativeSaleCate> update(AutomativeSaleCate m);
    Mono<Void> deleteById(final String id);
    Flux<AutomativeSaleCate> findByBusinessid(final String id);
    Flux<AutomativeSaleCate> findByBusinessidInc(final String id, final Date date);
    Flux<AutomativeSaleCate> findDynamics(final String id);
    Flux<AutomativeSaleCate> findDynamicsInc(final String id, final Date date);
    Flux<AutomativeSaleCate> findByRegion(final String region);
    Flux<AutomativeSaleCate> findByRegionInc(final String region, final Date date);
}