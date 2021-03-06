package com.spontzoug.server.repository;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.RetailerSetting;
import com.spontzoug.server.model.HealthSetting;
import com.spontzoug.server.model.Setting;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IRetailerSettingRepository extends ReactiveMongoRepository<RetailerSetting, String> {
    @Query("{'businessid':?0}")
    Flux<RetailerSetting> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<RetailerSetting> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<RetailerSetting> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<RetailerSetting> findDynamicsInc(final String id, final Date date);
    @Query(value = "{}" )
    Flux<RetailerSetting> findAll();
    @Query(value="{'modified':{'$gt':?0 }")
    Flux<RetailerSetting> findAllInc(final Date date );
    @Query(value="{'region':?0 }")
    Flux<RetailerSetting> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'$gt':?1 }")
    Flux<RetailerSetting> findByRegionInc(final String region, final Date date );
}