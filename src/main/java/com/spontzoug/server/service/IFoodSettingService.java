package com.spontzoug.server.service;


import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.FoodSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IFoodSettingService {
    void create(FoodSetting b);
    Mono<FoodSetting> findById(final String id);
    Mono<FoodSetting> update(FoodSetting service);
    Mono<Void> deleteById(final String id);
    Flux<FoodSetting> findByBusinessid(final String id);
    Flux<FoodSetting> findByBusinessidInc(final String id, final Date date);
    Flux<FoodSetting> findDynamics(final String id);
    Flux<FoodSetting> findDynamicsInc(final String id, final Date date);
    Flux<FoodSetting> findByRegion(final String region);
    Flux<FoodSetting> findByRegionInc(final String region, final Date date);
    Flux<FoodSetting> findAllInc(final Date date);
    Flux<FoodSetting> findAll();
}