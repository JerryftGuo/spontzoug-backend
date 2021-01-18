package com.spontzoug.server.service;


import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.AutomotiveSaleSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomotiveSaleSettingService {
    void create(AutomotiveSaleSetting b);
    Mono<AutomotiveSaleSetting> findById(final String id);
    Mono<AutomotiveSaleSetting> update(AutomotiveSaleSetting service);
    Mono<Void> deleteById(final String id);
    Flux<AutomotiveSaleSetting> findByBusinessid(final String id);
    Flux<AutomotiveSaleSetting> findByBusinessidInc(final String id, final Date date);
    Flux<AutomotiveSaleSetting> findDynamics(final String id);
    Flux<AutomotiveSaleSetting> findDynamicsInc(final String id, final Date date);
    Flux<AutomotiveSaleSetting> findByRegion(final String region);
    Flux<AutomotiveSaleSetting> findByRegionInc(final String region, final Date date);
    Flux<AutomotiveSaleSetting> findAllInc(final Date date);
    Flux<AutomotiveSaleSetting> findAll();
}