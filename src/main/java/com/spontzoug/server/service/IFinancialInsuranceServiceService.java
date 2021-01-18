package com.spontzoug.server.service;

import com.spontzoug.server.model.FinancialInsuranceService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IFinancialInsuranceServiceService {
    void create(FinancialInsuranceService b);
    Mono<FinancialInsuranceService> findById(final String id);
    Mono<FinancialInsuranceService> update(FinancialInsuranceService service);
    Mono<Void> deleteById(final String id);
    Flux<FinancialInsuranceService> findByBusinessid(final String id);
    Flux<FinancialInsuranceService> findByBusinessidInc(final String id, final Date date);
    Flux<FinancialInsuranceService> findDynamics(final String id);
    Flux<FinancialInsuranceService> findDynamicsInc(final String id, final Date date);
    Flux<FinancialInsuranceService> findByRegion(final String id);
    Flux<FinancialInsuranceService> findByRegionInc(final String id, final Date date);
    Mono<Long> countActive(final String id);
}