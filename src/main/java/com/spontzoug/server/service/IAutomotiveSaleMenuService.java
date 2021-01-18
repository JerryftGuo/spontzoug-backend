package com.spontzoug.server.service;

import com.spontzoug.server.model.AutomotiveSaleMenu;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomotiveSaleMenuService {
    void create(AutomotiveSaleMenu b);
    Mono<AutomotiveSaleMenu> findById(final String id);
    Mono<AutomotiveSaleMenu> update(AutomotiveSaleMenu menu);
    Mono<Void> deleteById(final String id);
    Flux<AutomotiveSaleMenu> findByBusinessid(final String id);
    Flux<AutomotiveSaleMenu> findByBusinessidInc(final String id, final Date date);
    Flux<AutomotiveSaleMenu> findDynamics(final String id);
    Flux<AutomotiveSaleMenu> findDynamicsInc(final String id, final Date date);
    Flux<AutomotiveSaleMenu> findByRegion(final String region);
    Flux<AutomotiveSaleMenu> findByRegionInc(final String region, final Date date);
    Mono<Long> countActive(final String id);
    Mono<Long> countDeleting(final String id, final Date date1, final Date date2);
}