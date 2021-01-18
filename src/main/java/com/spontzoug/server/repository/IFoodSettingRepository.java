package com.spontzoug.server.repository;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.HealthSetting;
import com.spontzoug.server.model.Setting;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IFoodSettingRepository extends ReactiveMongoRepository<FoodSetting, String> {
    @Query("{'businessid':?0}")
    Flux<FoodSetting> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<FoodSetting> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<FoodSetting> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<FoodSetting> findDynamicsInc(final String id, final Date date);
    @Query(value = "{}" )
    Flux<FoodSetting> findAll();
    @Query(value="{'modified':{'$gt':?0 }")
    Flux<FoodSetting> findAllInc(final Date date );
    @Query(value="{'region':?0 }")
    Flux<FoodSetting> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'$gt':?1 }")
    Flux<FoodSetting> findByRegionInc(final String region, final Date date );
}