package com.spontzoug.server.repository;

import com.spontzoug.server.model.FoodCate;
import com.spontzoug.server.model.Promotion;
import com.spontzoug.server.model.AutomotiveSaleService;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomotiveSaleServiceRepository extends ReactiveMongoRepository<AutomotiveSaleService,String> {
    @Query(value="{'businessid':?0}")
    Flux<AutomotiveSaleService> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<AutomotiveSaleService> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}",fields="{'description':0, 'created':0, 'modified':0}")
    Flux<AutomotiveSaleService> findDynamicsInc(final String id, final Date date);
    @Query(value="{'businessid':?0}", fields="{'description':0, 'created':0, 'modified':0}")
    Flux<AutomotiveSaleService> findDynamics(final String id);
    @Query(value="{'region':?0 }", fields="{'created':0,'modified':0,'creator':0}")
    Flux<AutomotiveSaleService> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'gt':?1 }",fields="{'created':0,'modified':0,'creator':0}")
    Flux<AutomotiveSaleService> findByRegionInc(final String region, final Date date );

    @Query(value="{'businessid':?0}",  count = true )
    Mono<Long> countActive(final String id);

}