package com.spontzoug.server.service;


import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.HealthSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IHealthSettingService {
    void create(HealthSetting b);
    Mono<HealthSetting> findById(final String id);
    Mono<HealthSetting> update(HealthSetting service);
    Mono<Void> deleteById(final String id);
    Flux<HealthSetting> findByBusinessid(final String id);
    Flux<HealthSetting> findByBusinessidInc(final String id, final Date date);
    Flux<HealthSetting> findDynamics(final String id);
    Flux<HealthSetting> findDynamicsInc(final String id, final Date date);
    Flux<HealthSetting> findByRegion(final String region);
    Flux<HealthSetting> findByRegionInc(final String region, final Date date);
    Flux<HealthSetting> findAllInc(final Date date);
    Flux<HealthSetting> findAll();
}