package com.spontzoug.server.service;

import com.spontzoug.server.model.Practitioner;
import com.spontzoug.server.model.SalesPerson;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ISalesPersonService {
    void create(SalesPerson b);
    Mono<SalesPerson> findById(final String id);
    Mono<SalesPerson> update(SalesPerson receptionist);
    Mono<Void> deleteById(final String id);
    Flux<SalesPerson> findByBusinessid(final String id);
    Flux<SalesPerson> findByBusinessidInc(final String id,final Date date);
    Flux<SalesPerson> findDynamics(final String id);
    Flux<SalesPerson> findDynamicsInc(final String id, final Date date);
}