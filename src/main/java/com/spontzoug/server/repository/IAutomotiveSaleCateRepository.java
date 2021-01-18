package com.spontzoug.server.repository;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.AutomotiveSaleCate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IAutomotiveSaleCateRepository extends ReactiveMongoRepository<AutomotiveSaleCate, String> {
    @Query(value="{'businessid':?0}")
    Flux<AutomotiveSaleCate> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<AutomotiveSaleCate> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0}")
    Flux<AutomotiveSaleCate> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<AutomotiveSaleCate> findDynamicsInc(final String id, final Date date);

    @Query(value="{'region':?0 }", fields="{'created':0,'modified':0,'creator':0}")
    Flux<AutomotiveSaleCate> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'gt':?1 }",fields="{'created':0,'modified':0,'creator':0}")
    Flux<AutomotiveSaleCate> findByRegionInc(final String region, final Date date );
}