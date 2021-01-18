package com.spontzoug.server.service;

import com.spontzoug.server.model.TrainingClasses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ITrainingClassesService {
    void create(TrainingClasses b);
    Mono<TrainingClasses> findById(final String id);
    Mono<TrainingClasses> update(TrainingClasses service);
    Mono<Void> deleteById(final String id);
    Flux<TrainingClasses> findByBusinessid(final String id);
    Flux<TrainingClasses> findByBusinessidInc(final String id, final Date date);
    Flux<TrainingClasses> findDynamics(final String id);
    Flux<TrainingClasses> findDynamicsInc(final String id, final Date date);
    Flux<TrainingClasses> findByRegion(final String id);
    Flux<TrainingClasses> findByRegionInc(final String id, final Date date);
    Mono<Long> countActive(final String id);
}