package com.spontzoug.server.service;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.RealEstateMenu;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IRealEstateMenuService {
    void create(RealEstateMenu b);
    Mono<RealEstateMenu> findById(final String id);
    Mono<RealEstateMenu> update(RealEstateMenu menu);
    Mono<Void> deleteById(final String id);
    Flux<RealEstateMenu> findByBusinessid(final String id);
    Flux<RealEstateMenu> findByBusinessidInc(final String id, final Date date);
    Flux<RealEstateMenu> findDynamics(final String id);
    Flux<RealEstateMenu> findDynamicsInc(final String id, final Date date);
    Flux<RealEstateMenu> findByRegion(final String region);
    Flux<RealEstateMenu> findByRegionInc(final String region, final Date date);
    Mono<Long> countActive(final String id);
    Mono<Long> countDeleting(final String id, final Date date1, final Date date2);
}