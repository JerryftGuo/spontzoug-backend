package com.spontzoug.server.service;


import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.AutomativeSaleSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomativeSaleSettingService {
    void create(AutomativeSaleSetting b);
    Mono<AutomativeSaleSetting> findById(final String id);
    Mono<AutomativeSaleSetting> update(AutomativeSaleSetting service);
    Mono<Void> deleteById(final String id);
    Flux<AutomativeSaleSetting> findByBusinessid(final String id);
    Flux<AutomativeSaleSetting> findByBusinessidInc(final String id, final Date date);
    Flux<AutomativeSaleSetting> findDynamics(final String id);
    Flux<AutomativeSaleSetting> findDynamicsInc(final String id, final Date date);
    Flux<AutomativeSaleSetting> findByRegion(final String region);
    Flux<AutomativeSaleSetting> findByRegionInc(final String region, final Date date);
    Flux<AutomativeSaleSetting> findAllInc(final Date date);
    Flux<AutomativeSaleSetting> findAll();
}