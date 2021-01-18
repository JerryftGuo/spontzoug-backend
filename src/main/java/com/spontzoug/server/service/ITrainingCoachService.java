package com.spontzoug.server.service;

import com.spontzoug.server.model.TrainingCoach;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ITrainingCoachService {
    void create(TrainingCoach b) ;
    Mono<TrainingCoach> findById(final String id);
    Mono<TrainingCoach> update(TrainingCoach coach);
    Mono<Void> deleteById(final String id);
    Flux<TrainingCoach> findByBusinessid(final String id);
    Flux<TrainingCoach> findByBusinessidInc(final String id, final Date date);
    Flux<TrainingCoach> findDynamics(final String id);
    Flux<TrainingCoach> findDynamicsInc(final String id, final Date date);
}