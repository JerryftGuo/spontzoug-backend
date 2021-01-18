package com.spontzoug.server.service;

import com.spontzoug.server.model.Promotion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IPromotionService {
    void create(Promotion b);
    Mono<Promotion> findById(final String id);
    Mono<Promotion> update(Promotion room);
    Mono<Void> deleteById(final String id);
    Flux<Promotion> findByBusinessid(final String id);
    Flux<Promotion> findByBusinessidInc(final String id, final Date date);
    Flux<Promotion> findDynamics(final String id);
    Flux<Promotion> findDynamicsInc(final String id, final Date date);
}