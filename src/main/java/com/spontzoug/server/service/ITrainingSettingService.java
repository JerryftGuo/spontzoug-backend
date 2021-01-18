package com.spontzoug.server.service;


import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.TrainingSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ITrainingSettingService {
    void create(TrainingSetting b);
    Mono<TrainingSetting> findById(final String id);
    Mono<TrainingSetting> update(TrainingSetting service);
    Mono<Void> deleteById(final String id);
    Flux<TrainingSetting> findByBusinessid(final String id);
    Flux<TrainingSetting> findByBusinessidInc(final String id, final Date date);
    Flux<TrainingSetting> findDynamics(final String id);
    Flux<TrainingSetting> findDynamicsInc(final String id, final Date date);
    Flux<TrainingSetting> findByRegion(final String region);
    Flux<TrainingSetting> findByRegionInc(final String region, final Date date);
    Flux<TrainingSetting> findAllInc(final Date date);
    Flux<TrainingSetting> findAll();
}