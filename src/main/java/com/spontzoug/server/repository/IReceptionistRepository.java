package com.spontzoug.server.repository;

import com.spontzoug.server.model.Promotion;
import com.spontzoug.server.model.Receptionist;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IReceptionistRepository extends ReactiveMongoRepository<Receptionist,String> {
    @Query(value="{'businessid':?0}")
    Flux<Receptionist> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Receptionist> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0}")
    Flux<Receptionist> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Receptionist> findDynamicsInc(final String id, final Date date);

    //@Query(value="{'businessid':?0,'$or': {'closed':false,'$and':{'closed':true,'modified':{'$gt': ?1, '$gt': ?2} }} }",  count = true )
    @Query(value="{'businessid':?0, 'closed':false }",  count = true )
    Mono<Long> countActive(final String id);

    @Query(value="{'businessid':?0,'closed':true,'modified':{'$gt': ?1, '$lt': ?2} }",  count = true )
    Mono<Long> countClosingReceptionist(final String id, final Date date1, final Date date2);

}