package com.spontzoug.server.repository;

import com.spontzoug.server.model.*;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IPersonalCareSettingRepository extends ReactiveMongoRepository<PersonalCareSetting, String> {
    @Query("{'businessid':?0}")
    Flux<PersonalCareSetting> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<PersonalCareSetting> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<PersonalCareSetting> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<PersonalCareSetting> findDynamicsInc(final String id, final Date date);
    @Query(value = "{}" )
    Flux<PersonalCareSetting> findAll();
    @Query(value="{'modified':{'$gt':?0 }")
    Flux<PersonalCareSetting> findAllInc(final Date date );
    @Query(value="{'region':?0 }")
    Flux<PersonalCareSetting> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'$gt':?1 }")
    Flux<PersonalCareSetting> findByRegionInc(final String region, final Date date );
}