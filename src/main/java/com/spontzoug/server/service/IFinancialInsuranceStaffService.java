package com.spontzoug.server.service;

import com.spontzoug.server.model.FinancialInsuranceStaff;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IFinancialInsuranceStaffService {
    void create(FinancialInsuranceStaff b) ;
    Mono<FinancialInsuranceStaff> findById(final String id);
    Mono<FinancialInsuranceStaff> update(FinancialInsuranceStaff practitioner);
    Mono<Void> deleteById(final String id);
    Flux<FinancialInsuranceStaff> findByBusinessid(final String id);
    Flux<FinancialInsuranceStaff> findByBusinessidInc(final String id, final Date date);
    Flux<FinancialInsuranceStaff> findDynamics(final String id);
    Flux<FinancialInsuranceStaff> findDynamicsInc(final String id, final Date date);
}