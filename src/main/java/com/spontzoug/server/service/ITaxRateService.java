package com.spontzoug.server.service;


import com.spontzoug.server.model.TaxRate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ITaxRateService {
    void create(TaxRate b);
    Mono<TaxRate> findById(final String id);
    Mono<TaxRate> update(TaxRate service);
    Mono<Void> deleteById(final String id);
    Flux<TaxRate> findByBusinessid(final String id);
    Flux<TaxRate> findByBusinessidInc(final String id, final Date date);
    Flux<TaxRate> findDynamics(final String id);
    Flux<TaxRate> findDynamicsInc(final String id, final Date date);
}