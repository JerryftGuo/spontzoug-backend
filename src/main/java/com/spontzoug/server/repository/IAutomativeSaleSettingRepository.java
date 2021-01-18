package com.spontzoug.server.repository;

import com.spontzoug.server.model.*;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IAutomativeSaleSettingRepository extends ReactiveMongoRepository<AutomativeSaleSetting, String> {
    @Query("{'businessid':?0}")
    Flux<AutomativeSaleSetting> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<AutomativeSaleSetting> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<AutomativeSaleSetting> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<AutomativeSaleSetting> findDynamicsInc(final String id, final Date date);
    @Query(value = "{}" )
    Flux<AutomativeSaleSetting> findAll();
    @Query(value="{'modified':{'$gt':?0 }")
    Flux<AutomativeSaleSetting> findAllInc(final Date date );
    @Query(value="{'region':?0 }")
    Flux<AutomativeSaleSetting> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'$gt':?1 }")
    Flux<AutomativeSaleSetting> findByRegionInc(final String region, final Date date );
}