package com.spontzoug.server.service;


import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.RetailerSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IRetailerSettingService {
    void create(RetailerSetting b);
    Mono<RetailerSetting> findById(final String id);
    Mono<RetailerSetting> update(RetailerSetting service);
    Mono<Void> deleteById(final String id);
    Flux<RetailerSetting> findByBusinessid(final String id);
    Flux<RetailerSetting> findByBusinessidInc(final String id, final Date date);
    Flux<RetailerSetting> findDynamics(final String id);
    Flux<RetailerSetting> findDynamicsInc(final String id, final Date date);
    Flux<RetailerSetting> findByRegion(final String region);
    Flux<RetailerSetting> findByRegionInc(final String region, final Date date);
    Flux<RetailerSetting> findAllInc(final Date date);
    Flux<RetailerSetting> findAll();
}