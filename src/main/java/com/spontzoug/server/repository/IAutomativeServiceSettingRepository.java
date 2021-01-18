package com.spontzoug.server.repository;

import com.spontzoug.server.model.*;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IAutomativeServiceSettingRepository extends ReactiveMongoRepository<AutomativeServiceSetting, String> {
    @Query("{'businessid':?0}")
    Flux<AutomativeServiceSetting> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<AutomativeServiceSetting> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<AutomativeServiceSetting> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<AutomativeServiceSetting> findDynamicsInc(final String id, final Date date);
    @Query(value = "{}" )
    Flux<AutomativeServiceSetting> findAll();
    @Query(value="{'modified':{'$gt':?0 }")
    Flux<AutomativeServiceSetting> findAllInc(final Date date );
    @Query(value="{'region':?0 }")
    Flux<AutomativeServiceSetting> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'$gt':?1 }")
    Flux<AutomativeServiceSetting> findByRegionInc(final String region, final Date date );
}