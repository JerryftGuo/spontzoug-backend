package com.spontzoug.server.service;

import com.spontzoug.server.model.RetailerCate;
import com.spontzoug.server.model.RetailerMenu;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IRetailerCateService {
    void create(RetailerCate m);
    Mono<RetailerCate> findById(final String id);
    Mono<RetailerCate> update(RetailerCate m);
    Mono<Void> deleteById(final String id);
    Flux<RetailerCate> findByBusinessid(final String id);
    Flux<RetailerCate> findByBusinessidInc(final String id, final Date date);
    Flux<RetailerCate> findDynamics(final String id);
    Flux<RetailerCate> findDynamicsInc(final String id, final Date date);
    Flux<RetailerCate> findByRegion(final String region);
    Flux<RetailerCate> findByRegionInc(final String region, final Date date);
}