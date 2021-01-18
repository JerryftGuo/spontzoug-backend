package com.spontzoug.server.service;


import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.RealEstateSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IRealEstateSettingService {
    void create(RealEstateSetting b);
    Mono<RealEstateSetting> findById(final String id);
    Mono<RealEstateSetting> update(RealEstateSetting service);
    Mono<Void> deleteById(final String id);
    Flux<RealEstateSetting> findByBusinessid(final String id);
    Flux<RealEstateSetting> findByBusinessidInc(final String id, final Date date);
    Flux<RealEstateSetting> findDynamics(final String id);
    Flux<RealEstateSetting> findDynamicsInc(final String id, final Date date);
    Flux<RealEstateSetting> findByRegion(final String region);
    Flux<RealEstateSetting> findByRegionInc(final String region, final Date date);
    Flux<RealEstateSetting> findAllInc(final Date date);
    Flux<RealEstateSetting> findAll();
}