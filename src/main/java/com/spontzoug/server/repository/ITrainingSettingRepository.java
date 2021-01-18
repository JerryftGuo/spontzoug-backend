package com.spontzoug.server.repository;

import com.spontzoug.server.model.*;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface ITrainingSettingRepository extends ReactiveMongoRepository<TrainingSetting, String> {
    @Query("{'businessid':?0}")
    Flux<TrainingSetting> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<TrainingSetting> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<TrainingSetting> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<TrainingSetting> findDynamicsInc(final String id, final Date date);
    @Query(value = "{}" )
    Flux<TrainingSetting> findAll();
    @Query(value="{'modified':{'$gt':?0 }")
    Flux<TrainingSetting> findAllInc(final Date date );
    @Query(value="{'region':?0 }")
    Flux<TrainingSetting> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'$gt':?1 }")
    Flux<TrainingSetting> findByRegionInc(final String region, final Date date );
}