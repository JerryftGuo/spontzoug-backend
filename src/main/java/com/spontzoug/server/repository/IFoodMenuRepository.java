package com.spontzoug.server.repository;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.FoodMenu;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IFoodMenuRepository extends ReactiveMongoRepository<FoodMenu, String> {
    @Query(value="{'businessid':?0}")
    Flux<FoodMenu> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<FoodMenu> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0}")
    Flux<FoodMenu> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<FoodMenu> findDynamicsInc(final String id, final Date date);

    @Query(value="{'region':?0 }", fields="{'created':0,'modified':0,'creator':0}")
    Flux<FoodMenu> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'gt':?1 }",fields="{'created':0,'modified':0,'creator':0}")
    Flux<FoodMenu> findByRegionInc(final String region, final Date date );

    @Query(value="{'businessid':?0, 'deleted':false }",  count = true )
    Mono<Long> countActive(final String id);

    @Query(value="{'businessid':?0,'deleted':true,'modified':{'$gt': ?1, '$lt': ?2} }",  count = true )
    Mono<Long> countDeleting(final String id, final Date date1, final Date date2);

}