package com.spontzoug.server.service;


import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.ResidentialSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IResidentialSettingService {
    void create(ResidentialSetting b);
    Mono<ResidentialSetting> findById(final String id);
    Mono<ResidentialSetting> update(ResidentialSetting service);
    Mono<Void> deleteById(final String id);
    Flux<ResidentialSetting> findByBusinessid(final String id);
    Flux<ResidentialSetting> findByBusinessidInc(final String id, final Date date);
    Flux<ResidentialSetting> findDynamics(final String id);
    Flux<ResidentialSetting> findDynamicsInc(final String id, final Date date);
    Flux<ResidentialSetting> findByRegion(final String region);
    Flux<ResidentialSetting> findByRegionInc(final String region, final Date date);
    Flux<ResidentialSetting> findAllInc(final Date date);
    Flux<ResidentialSetting> findAll();
}