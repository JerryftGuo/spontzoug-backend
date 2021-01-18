package com.spontzoug.server.repository;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.RetailerCate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IRetailerCateRepository extends ReactiveMongoRepository<RetailerCate, String> {
    @Query(value="{'businessid':?0}")
    Flux<RetailerCate> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<RetailerCate> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0}")
    Flux<RetailerCate> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<RetailerCate> findDynamicsInc(final String id, final Date date);

    @Query(value="{'region':?0 }", fields="{'created':0,'modified':0,'creator':0}")
    Flux<RetailerCate> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'gt':?1 }",fields="{'created':0,'modified':0,'creator':0}")
    Flux<RetailerCate> findByRegionInc(final String region, final Date date );
}