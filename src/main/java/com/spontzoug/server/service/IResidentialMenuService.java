package com.spontzoug.server.service;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.ResidentialMenu;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IResidentialMenuService {
    void create(ResidentialMenu b);
    Mono<ResidentialMenu> findById(final String id);
    Mono<ResidentialMenu> update(ResidentialMenu menu);
    Mono<Void> deleteById(final String id);
    Flux<ResidentialMenu> findByBusinessid(final String id);
    Flux<ResidentialMenu> findByBusinessidInc(final String id, final Date date);
    Flux<ResidentialMenu> findDynamics(final String id);
    Flux<ResidentialMenu> findDynamicsInc(final String id, final Date date);
    Flux<ResidentialMenu> findByRegion(final String region);
    Flux<ResidentialMenu> findByRegionInc(final String region, final Date date);
    Mono<Long> countActive(final String id);
    Mono<Long> countDeleting(final String id, final Date date1, final Date date2);
}