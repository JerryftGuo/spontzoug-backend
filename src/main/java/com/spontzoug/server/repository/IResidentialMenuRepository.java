package com.spontzoug.server.repository;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.ResidentialMenu;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IResidentialMenuRepository extends ReactiveMongoRepository<ResidentialMenu, String> {
    @Query(value="{'businessid':?0}")
    Flux<ResidentialMenu> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<ResidentialMenu> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0}")
    Flux<ResidentialMenu> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<ResidentialMenu> findDynamicsInc(final String id, final Date date);

    @Query(value="{'region':?0 }", fields="{'created':0,'modified':0,'creator':0}")
    Flux<ResidentialMenu> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'gt':?1 }",fields="{'created':0,'modified':0,'creator':0}")
    Flux<ResidentialMenu> findByRegionInc(final String region, final Date date );

    @Query(value="{'businessid':?0, 'deleted':false }",  count = true )
    Mono<Long> countActive(final String id);

    @Query(value="{'businessid':?0,'deleted':true,'modified':{'$gt': ?1, '$lt': ?2} }",  count = true )
    Mono<Long> countDeleting(final String id, final Date date1, final Date date2);

}