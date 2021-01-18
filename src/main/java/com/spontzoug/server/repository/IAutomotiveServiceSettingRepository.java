package com.spontzoug.server.repository;

import com.spontzoug.server.model.*;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IAutomotiveServiceSettingRepository extends ReactiveMongoRepository<AutomotiveServiceSetting, String> {
    @Query("{'businessid':?0}")
    Flux<AutomotiveServiceSetting> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<AutomotiveServiceSetting> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<AutomotiveServiceSetting> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<AutomotiveServiceSetting> findDynamicsInc(final String id, final Date date);
    @Query(value = "{}" )
    Flux<AutomotiveServiceSetting> findAll();
    @Query(value="{'modified':{'$gt':?0 }")
    Flux<AutomotiveServiceSetting> findAllInc(final Date date );
    @Query(value="{'region':?0 }")
    Flux<AutomotiveServiceSetting> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'$gt':?1 }")
    Flux<AutomotiveServiceSetting> findByRegionInc(final String region, final Date date );
}