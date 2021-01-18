package com.spontzoug.server.service;


import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.FinancialInsuranceSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IFinancialInsuranceSettingService {
    void create(FinancialInsuranceSetting b);
    Mono<FinancialInsuranceSetting> findById(final String id);
    Mono<FinancialInsuranceSetting> update(FinancialInsuranceSetting service);
    Mono<Void> deleteById(final String id);
    Flux<FinancialInsuranceSetting> findByBusinessid(final String id);
    Flux<FinancialInsuranceSetting> findByBusinessidInc(final String id, final Date date);
    Flux<FinancialInsuranceSetting> findDynamics(final String id);
    Flux<FinancialInsuranceSetting> findDynamicsInc(final String id, final Date date);
    Flux<FinancialInsuranceSetting> findByRegion(final String region);
    Flux<FinancialInsuranceSetting> findByRegionInc(final String region, final Date date);
    Flux<FinancialInsuranceSetting> findAllInc(final Date date);
    Flux<FinancialInsuranceSetting> findAll();
}