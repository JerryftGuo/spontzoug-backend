package com.spontzoug.server.repository;

import com.spontzoug.server.model.FoodCate;
import com.spontzoug.server.model.Promotion;
import com.spontzoug.server.model.AutomativeServiceService;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomativeServiceServiceRepository extends ReactiveMongoRepository<AutomativeServiceService,String> {
    @Query(value="{'businessid':?0}")
    Flux<AutomativeServiceService> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<AutomativeServiceService> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}",fields="{'description':0, 'created':0, 'modified':0}")
    Flux<AutomativeServiceService> findDynamicsInc(final String id, final Date date);
    @Query(value="{'businessid':?0}", fields="{'description':0, 'created':0, 'modified':0}")
    Flux<AutomativeServiceService> findDynamics(final String id);
    @Query(value="{'region':?0 }", fields="{'created':0,'modified':0,'creator':0}")
    Flux<AutomativeServiceService> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'gt':?1 }",fields="{'created':0,'modified':0,'creator':0}")
    Flux<AutomativeServiceService> findByRegionInc(final String region, final Date date );

    @Query(value="{'businessid':?0}",  count = true )
    Mono<Long> countActive(final String id);
}