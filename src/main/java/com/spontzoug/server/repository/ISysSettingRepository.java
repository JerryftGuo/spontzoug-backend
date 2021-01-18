package com.spontzoug.server.repository;

import com.spontzoug.server.model.SysSetting;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface ISysSettingRepository extends ReactiveMongoRepository<SysSetting, String> {
    @Query("{'businessid':?0}")
    Flux<SysSetting> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<SysSetting> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<SysSetting> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<SysSetting> findDynamicsInc(final String id, final Date date);
}