package com.spontzoug.server.repository;

import com.spontzoug.server.model.*;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IAutomotiveSaleSettingRepository extends ReactiveMongoRepository<AutomotiveSaleSetting, String> {
    @Query("{'businessid':?0}")
    Flux<AutomotiveSaleSetting> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<AutomotiveSaleSetting> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<AutomotiveSaleSetting> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<AutomotiveSaleSetting> findDynamicsInc(final String id, final Date date);
    @Query(value = "{}" )
    Flux<AutomotiveSaleSetting> findAll();
    @Query(value="{'modified':{'$gt':?0 }")
    Flux<AutomotiveSaleSetting> findAllInc(final Date date );
    @Query(value="{'region':?0 }")
    Flux<AutomotiveSaleSetting> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'$gt':?1 }")
    Flux<AutomotiveSaleSetting> findByRegionInc(final String region, final Date date );
}