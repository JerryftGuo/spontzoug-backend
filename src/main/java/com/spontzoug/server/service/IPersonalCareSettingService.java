package com.spontzoug.server.service;


import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.PersonalCareSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IPersonalCareSettingService {
    void create(PersonalCareSetting b);
    Mono<PersonalCareSetting> findById(final String id);
    Mono<PersonalCareSetting> update(PersonalCareSetting service);
    Mono<Void> deleteById(final String id);
    Flux<PersonalCareSetting> findByBusinessid(final String id);
    Flux<PersonalCareSetting> findByBusinessidInc(final String id, final Date date);
    Flux<PersonalCareSetting> findDynamics(final String id);
    Flux<PersonalCareSetting> findDynamicsInc(final String id, final Date date);
    Flux<PersonalCareSetting> findByRegion(final String region);
    Flux<PersonalCareSetting> findByRegionInc(final String region, final Date date);
    Flux<PersonalCareSetting> findAllInc(final Date date);
    Flux<PersonalCareSetting> findAll();
}