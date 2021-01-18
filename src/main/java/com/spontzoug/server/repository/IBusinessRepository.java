package com.spontzoug.server.repository;

import com.spontzoug.server.model.Business;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IBusinessRepository extends ReactiveMongoRepository<Business, String> {
    @Query(value="{'address.province':?0 }", fields="{'description':0,'background':0,'logo':0}")
    Flux<Business> findByRegion(final String region );
    @Query(value="{'address.province':?0,'modified':{'gt':?1 }}",fields="{'description':0,'background':0,'logo':0}")
    Flux<Business> findByRegionInc(final String region, final Date date );
    @Query(value = "{}" )
    Flux<Business> findAll();
    @Query(value="{'modified':{'gt':?0 }")
    Flux<Business> findAllInc(final Date date );

    @Query(value="{'creator':?0}")
    Flux<Business> findByCreator(final String creator);

    @Query(value="{'id':?0}")
    Flux<Business> findDynamics(final String id);
    @Query(value="{'id':?0,'modified':{'$gt':?1}}")
    Flux<Business> findDynamicsInc(final String id,final Date date);
    @Query("{'id':?0}")
    Flux<Business> findByBusinessid(final String id);
    @Query(value="{'id':?0,'modified':{'$gt':?1}}")
    Flux<Business> findByBusinessidInc(final String id,final Date date);

    @Query(value="{'id':?0,'modified':{'$gt':?1}}")
    Mono<Business> findByIdInc(final String id,final Date date);

    @Query(value="{'address.province':?0,'billingday':?1}")
    Flux<Business> findByBillingDay(final String region,final Integer day);

    @Query(value="{'address.province':?0,'billingday':?1}", count=true)
    Mono<Long> countByRegionDay(final String region, final Integer day);

    @Query(value="{'start':{'$gt':?0, '$lt': ?1}}",
            fields="{'id':0, 'businessname':0, 'companyname':0, 'outline':0, 'description':0,'subtype':0, 'address':0, 'creator':0}")
    Flux<Business> findDayReport(final Date date1, final Date date2);
    @Query(value="{'start':{'$gt':?0, '$lt': ?1}}",
            fields="{'id':0, 'businessname':0, 'companyname':0, 'outline':0, 'description':0,'subtype':0, 'address':0, 'creator':0}")
    Flux<Business> findMonthReport(final Date date1, final Date date2);

}