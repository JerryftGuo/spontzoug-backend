package com.spontzoug.server.repository;

import com.spontzoug.server.model.*;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IHealthSettingRepository extends ReactiveMongoRepository<HealthSetting, String> {
    @Query("{'businessid':?0}")
    Flux<HealthSetting> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<HealthSetting> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<HealthSetting> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<HealthSetting> findDynamicsInc(final String id, final Date date);
    @Query(value = "{}" )
    Flux<HealthSetting> findAll();
    @Query(value="{'modified':{'$gt':?0 }")
    Flux<HealthSetting> findAllInc(final Date date );
    @Query(value="{'region':?0 }")
    Flux<HealthSetting> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'$gt':?1 }")
    Flux<HealthSetting> findByRegionInc(final String region, final Date date );
}