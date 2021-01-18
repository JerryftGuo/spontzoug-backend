package com.spontzoug.server.repository;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.AutomativeSaleCate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IAutomativeSaleCateRepository extends ReactiveMongoRepository<AutomativeSaleCate, String> {
    @Query(value="{'businessid':?0}")
    Flux<AutomativeSaleCate> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<AutomativeSaleCate> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0}")
    Flux<AutomativeSaleCate> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<AutomativeSaleCate> findDynamicsInc(final String id, final Date date);

    @Query(value="{'region':?0 }", fields="{'created':0,'modified':0,'creator':0}")
    Flux<AutomativeSaleCate> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'gt':?1 }",fields="{'created':0,'modified':0,'creator':0}")
    Flux<AutomativeSaleCate> findByRegionInc(final String region, final Date date );
}