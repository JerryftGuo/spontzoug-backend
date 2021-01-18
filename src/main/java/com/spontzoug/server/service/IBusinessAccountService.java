package com.spontzoug.server.service;

import com.spontzoug.server.model.BusinessAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface  IBusinessAccountService {
    void create(BusinessAccount b);
    Mono<BusinessAccount> findById(final String id);
    Mono<BusinessAccount> update(BusinessAccount business);
    Mono<Void> deleteById(final String id);
    Flux<BusinessAccount> findByRegion(final String region);
    Flux<BusinessAccount> findByRegionInc(final String region, final Date date);
    Flux<BusinessAccount> findAllInc(final Date date);
    Flux<BusinessAccount> findByCreator(final String creator);
    Flux<BusinessAccount> findAll();
    Flux<BusinessAccount> findDynamics(final String id);
    Flux<BusinessAccount> findDynamicsInc(final String id, final Date date);
    Flux<BusinessAccount> findByBusinessid(final String id);
    Flux<BusinessAccount> findByBusinessidInc(final String id, final Date date);

}