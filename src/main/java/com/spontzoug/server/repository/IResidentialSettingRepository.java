package com.spontzoug.server.repository;

import com.spontzoug.server.model.*;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IResidentialSettingRepository extends ReactiveMongoRepository<ResidentialSetting, String> {
    @Query("{'businessid':?0}")
    Flux<ResidentialSetting> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<ResidentialSetting> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<ResidentialSetting> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<ResidentialSetting> findDynamicsInc(final String id, final Date date);
    @Query(value = "{}" )
    Flux<ResidentialSetting> findAll();
    @Query(value="{'modified':{'$gt':?0 }")
    Flux<ResidentialSetting> findAllInc(final Date date );
    @Query(value="{'region':?0 }")
    Flux<ResidentialSetting> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'$gt':?1 }")
    Flux<ResidentialSetting> findByRegionInc(final String region, final Date date );
}