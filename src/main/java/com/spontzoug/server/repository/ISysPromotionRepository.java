package com.spontzoug.server.repository;

import com.spontzoug.server.model.SysPromotion;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface ISysPromotionRepository extends ReactiveMongoRepository<SysPromotion, String> {
    @Query("{'businessid':?0}")
    Flux<SysPromotion> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<SysPromotion> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<SysPromotion> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<SysPromotion> findDynamicsInc(final String id, final Date date);
}