package com.spontzoug.server.repository;

import com.spontzoug.server.model.Cashier;
import com.spontzoug.server.model.Cashier;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ICashierRepository extends ReactiveMongoRepository<Cashier,String> {
    @Query(value="{'businessid':?0}")
    Flux<Cashier> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Cashier> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0}")
    Flux<Cashier> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Cashier> findDynamicsInc(final String id, final Date date);

    @Query(value="{'businessid':?0, 'closed':false }",  count = true )
    Mono<Long> countActive(final String id);

    @Query(value="{'businessid':?0,'closed':true,'modified':{'$gt': ?1, '$lt': ?2} }",  count = true )
    Mono<Long> countClosingCashier(final String id, final Date date1, final Date date2);


}