package com.spontzoug.server.service;


import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.AutomativeServiceSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomativeServiceSettingService {
    void create(AutomativeServiceSetting b);
    Mono<AutomativeServiceSetting> findById(final String id);
    Mono<AutomativeServiceSetting> update(AutomativeServiceSetting service);
    Mono<Void> deleteById(final String id);
    Flux<AutomativeServiceSetting> findByBusinessid(final String id);
    Flux<AutomativeServiceSetting> findByBusinessidInc(final String id, final Date date);
    Flux<AutomativeServiceSetting> findDynamics(final String id);
    Flux<AutomativeServiceSetting> findDynamicsInc(final String id, final Date date);
    Flux<AutomativeServiceSetting> findByRegion(final String region);
    Flux<AutomativeServiceSetting> findByRegionInc(final String region, final Date date);
    Flux<AutomativeServiceSetting> findAllInc(final Date date);
    Flux<AutomativeServiceSetting> findAll();
}