package com.spontzoug.server.repository;

import com.spontzoug.server.model.Notice;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface INoticeRepository extends ReactiveMongoRepository<Notice, String> {
    @Query("{'businessid':?0}")
    Flux<Notice> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Notice> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<Notice> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Notice> findDynamicsInc(final String id, final Date date);
    
}