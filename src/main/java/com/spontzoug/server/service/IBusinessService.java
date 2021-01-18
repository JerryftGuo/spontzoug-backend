package com.spontzoug.server.service;

import com.spontzoug.server.model.Business;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface  IBusinessService {
    void create(Business b);
    Mono<Business> findById(final String id);
    Mono<Business> findByIdInc(final String id, final Date date);
    Mono<Business> update(Business business);
    Mono<Void> deleteById(final String id);
    Flux<Business> findByRegion(final String region);
    Flux<Business> findByRegionInc(final String region, final Date date);
    Flux<Business> findAllInc(final Date date);
    Flux<Business> findByCreator(final String creator);
    Flux<Business> findAll();
    Flux<Business> findDynamics(final String id);
    Flux<Business> findDynamicsInc(final String id, final Date date);
    Flux<Business> findByBusinessid(final String id);
    Flux<Business> findByBusinessidInc(final String id, final Date date);
    Flux<Business> findDayReport(
            final Date date1, final Date date2);
    Flux<Business> findMonthReport(
            final Date date1, final Date date2);
    Mono<Long> countByRegionDay(final String region, final Integer day);
    Flux<Business> findByBillingDay(final String region, final Integer day);
}