package com.spontzoug.server.repository;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.RealEstateSetting;
import com.spontzoug.server.model.HealthSetting;
import com.spontzoug.server.model.Setting;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IRealEstateSettingRepository extends ReactiveMongoRepository<RealEstateSetting, String> {
    @Query("{'businessid':?0}")
    Flux<RealEstateSetting> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<RealEstateSetting> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<RealEstateSetting> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<RealEstateSetting> findDynamicsInc(final String id, final Date date);
    @Query(value = "{}" )
    Flux<RealEstateSetting> findAll();
    @Query(value="{'modified':{'$gt':?0 }")
    Flux<RealEstateSetting> findAllInc(final Date date );
    @Query(value="{'region':?0 }")
    Flux<RealEstateSetting> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'$gt':?1 }")
    Flux<RealEstateSetting> findByRegionInc(final String region, final Date date );
}