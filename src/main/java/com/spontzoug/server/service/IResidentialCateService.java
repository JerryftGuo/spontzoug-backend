package com.spontzoug.server.service;

import com.spontzoug.server.model.ResidentialCate;
import com.spontzoug.server.model.ResidentialMenu;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IResidentialCateService {
    void create(ResidentialCate m);
    Mono<ResidentialCate> findById(final String id);
    Mono<ResidentialCate> update(ResidentialCate m);
    Mono<Void> deleteById(final String id);
    Flux<ResidentialCate> findByBusinessid(final String id);
    Flux<ResidentialCate> findByBusinessidInc(final String id, final Date date);
    Flux<ResidentialCate> findDynamics(final String id);
    Flux<ResidentialCate> findDynamicsInc(final String id, final Date date);
    Flux<ResidentialCate> findByRegion(final String region);
    Flux<ResidentialCate> findByRegionInc(final String region, final Date date);
}