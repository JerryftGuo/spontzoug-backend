package com.spontzoug.server.repository;

import com.spontzoug.server.model.Menu;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IMenuRepository extends ReactiveMongoRepository<Menu, String> {
    @Query(value="{'businessid':?0}")
    Flux<Menu> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Menu> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0}")
    Flux<Menu> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Menu> findDynamicsInc(final String id, final Date date);

    @Query(value="{'businessid':?0, 'deleted':false, 'modified':{'$gt': ?1, '$gt': ?2} }",  count = true )
    Mono<Long> countActiveMenu(final String id, final Date date1, final Date date2);

    @Query(value="{'businessid':?0,'deleted':true,'modified':{'$gt': ?1, '$lt': ?2} }",  count = true )
    Mono<Long> countDeletingMenu(final String id, final Date date1, final Date date2);

    @Query(value="{'businessid':?0, 'closed':false }",  count = true )
    Mono<Long> countActiveAllMenu(final String id);

}