package com.spontzoug.server.repository;

import com.spontzoug.server.model.PlanOption;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IPlanOptionRepository extends ReactiveMongoRepository<PlanOption, String> {
    @Query("{'businessid':?0}")
    Flux<PlanOption> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<PlanOption> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<PlanOption> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<PlanOption> findDynamicsInc(final String id, final Date date);
}