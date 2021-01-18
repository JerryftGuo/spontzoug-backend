package com.spontzoug.server.repository;

import com.spontzoug.server.model.Promotion;
import com.spontzoug.server.model.Accountant;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAccountantRepository extends ReactiveMongoRepository<Accountant,String> {
    @Query(value="{'businessid':?0}")
    Flux<Accountant> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Accountant> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0}")
    Flux<Accountant> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Accountant> findDynamicsInc(final String id, final Date date);

    @Query(value="{'businessid':?0,'closed':false }",  count = true )
    Mono<Long> countActiveAccountant(final String id);
    @Query(value="{'businessid':?0,'closed':true,'modified':{'$gt': ?1, '$lt': ?2} }",  count = true )
    Mono<Long> countClosingAccountant(final String id, final Date date1, final Date date2);

}