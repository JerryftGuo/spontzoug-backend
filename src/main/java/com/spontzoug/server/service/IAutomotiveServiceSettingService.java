package com.spontzoug.server.service;

import com.spontzoug.server.model.AutomotiveServiceSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomotiveServiceSettingService {
    void create(AutomotiveServiceSetting b);
    Mono<AutomotiveServiceSetting> findById(final String id);
    Mono<AutomotiveServiceSetting> update(AutomotiveServiceSetting service);
    Mono<Void> deleteById(final String id);
    Flux<AutomotiveServiceSetting> findByBusinessid(final String id);
    Flux<AutomotiveServiceSetting> findByBusinessidInc(final String id, final Date date);
    Flux<AutomotiveServiceSetting> findDynamics(final String id);
    Flux<AutomotiveServiceSetting> findDynamicsInc(final String id, final Date date);
    Flux<AutomotiveServiceSetting> findByRegion(final String region);
    Flux<AutomotiveServiceSetting> findByRegionInc(final String region, final Date date);
    Flux<AutomotiveServiceSetting> findAllInc(final Date date);
    Flux<AutomotiveServiceSetting> findAll();
}