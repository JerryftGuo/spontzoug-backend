package com.spontzoug.server.service;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.RetailerMenu;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IRetailerMenuService {
    void create(RetailerMenu b);
    Mono<RetailerMenu> findById(final String id);
    Mono<RetailerMenu> update(RetailerMenu menu);
    Mono<Void> deleteById(final String id);
    Flux<RetailerMenu> findByBusinessid(final String id);
    Flux<RetailerMenu> findByBusinessidInc(final String id, final Date date);
    Flux<RetailerMenu> findDynamics(final String id);
    Flux<RetailerMenu> findDynamicsInc(final String id, final Date date);
    Flux<RetailerMenu> findByRegion(final String region);
    Flux<RetailerMenu> findByRegionInc(final String region, final Date date);
    Mono<Long> countActive(final String id);
    Mono<Long> countDeleting(final String id, final Date date1, final Date date2);
}