package com.spontzoug.server.repository;

import com.spontzoug.server.model.Realtor;
import com.spontzoug.server.model.Realtor;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IRealtorRepository extends ReactiveMongoRepository<Realtor,String> {
    @Query(value="{'businessid':?0}")
    Flux<Realtor> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Realtor> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0}")
    Flux<Realtor> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Realtor> findDynamicsInc(final String id, final Date date);

    @Query(value="{'businessid':?0, 'closed':false }",  count = true )
    Mono<Long> countActive(final String id);

    @Query(value="{'businessid':?0,'closed':true,'modified':{'$gt': ?1, '$lt': ?2} }",  count = true )
    Mono<Long> countClosingRealtor(final String id, final Date date1, final Date date2);
    
}