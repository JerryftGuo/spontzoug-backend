package com.spontzoug.server.service;


import com.spontzoug.server.model.PlanOption;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IPlanOptionService {
    void create(PlanOption b);
    Mono<PlanOption> findById(final String id);
    Mono<PlanOption> update(PlanOption option);
    Mono<Void> deleteById(final String id);
    Flux<PlanOption> findByBusinessid(final String id);
    Flux<PlanOption> findByBusinessidInc(final String id, final Date date);
    Flux<PlanOption> findDynamics(final String id);
    Flux<PlanOption> findDynamicsInc(final String id, final Date date);
}