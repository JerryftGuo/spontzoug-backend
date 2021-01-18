package com.spontzoug.server.repository;

import com.spontzoug.server.model.Promotion;
import com.spontzoug.server.model.Setting;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface ISettingRepository extends ReactiveMongoRepository<Setting, String> {
    @Query("{'businessid':?0}")
    Flux<Setting> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Setting> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<Setting> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Setting> findDynamicsInc(final String id, final Date date);
}