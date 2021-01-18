package com.spontzoug.server.repository;

import com.spontzoug.server.model.SysNotice;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface ISysNoticeRepository extends ReactiveMongoRepository<SysNotice, String> {
    @Query("{'businessid':?0}")
    Flux<SysNotice> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<SysNotice> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<SysNotice> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<SysNotice> findDynamicsInc(final String id, final Date date);
    @Query(value="{}")
    Flux<SysNotice> findForBusiness();
    @Query(value="{'modified':{'$gte':?0}}")
    Flux<SysNotice> findForBusinessInc(final Date date);
}