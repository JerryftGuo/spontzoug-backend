package com.spontzoug.server.repository;

import com.spontzoug.server.model.Promotion;
import com.spontzoug.server.model.Assistant;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAssistantRepository extends ReactiveMongoRepository<Assistant,String> {
    @Query(value="{'businessid':?0}")
    Flux<Assistant> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Assistant> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0}")
    Flux<Assistant> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Assistant> findDynamicsInc(final String id, final Date date);
//    @Query(value="{'businessid':?0,'$or': {'closed':false,'$and':{'closed':true,'modified':{'$gt': ?1, '$gt': ?2} }} }",  count = true )
    @Query(value="{'businessid':?0, 'closed':false }",  count = true )
    Mono<Long> countActive(final String id );

    @Query(value="{'businessid':?0,'closed':true,'modified':{'$gt': ?1, '$lt': ?2} }",  count = true )
    Mono<Long> countClosingAssistant(final String id, final Date date1, final Date date2);

}