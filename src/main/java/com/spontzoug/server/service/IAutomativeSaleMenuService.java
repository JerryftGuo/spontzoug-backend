package com.spontzoug.server.service;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.AutomativeSaleMenu;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomativeSaleMenuService {
    void create(AutomativeSaleMenu b);
    Mono<AutomativeSaleMenu> findById(final String id);
    Mono<AutomativeSaleMenu> update(AutomativeSaleMenu menu);
    Mono<Void> deleteById(final String id);
    Flux<AutomativeSaleMenu> findByBusinessid(final String id);
    Flux<AutomativeSaleMenu> findByBusinessidInc(final String id, final Date date);
    Flux<AutomativeSaleMenu> findDynamics(final String id);
    Flux<AutomativeSaleMenu> findDynamicsInc(final String id, final Date date);
    Flux<AutomativeSaleMenu> findByRegion(final String region);
    Flux<AutomativeSaleMenu> findByRegionInc(final String region, final Date date);
    Mono<Long> countActive(final String id);
    Mono<Long> countDeleting(final String id, final Date date1, final Date date2);
}