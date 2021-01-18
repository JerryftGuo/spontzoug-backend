package com.spontzoug.server.service;


import com.spontzoug.server.model.SysPromotion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ISysPromotionService {
    void create(SysPromotion b);
    Mono<SysPromotion> findById(final String id);
    Mono<SysPromotion> update(SysPromotion service);
    Mono<Void> deleteById(final String id);
    Flux<SysPromotion> findByBusinessid(final String id);
    Flux<SysPromotion> findByBusinessidInc(final String id, final Date date);
    Flux<SysPromotion> findDynamics(final String id);
    Flux<SysPromotion> findDynamicsInc(final String id, final Date date);
}