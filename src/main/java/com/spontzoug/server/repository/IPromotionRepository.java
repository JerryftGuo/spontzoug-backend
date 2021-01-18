package com.spontzoug.server.repository;

import com.spontzoug.server.model.Promotion;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IPromotionRepository extends ReactiveMongoRepository<Promotion, String> {
    @Query(value="{'businessid':?0}")
    Flux<Promotion> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Promotion> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0}")
    Flux<Promotion> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Promotion> findDynamicsInc(final String id, final Date date);
}