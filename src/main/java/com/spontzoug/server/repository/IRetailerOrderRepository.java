package com.spontzoug.server.repository;


import com.spontzoug.server.http.RetailerOrderRegionReport;
import com.spontzoug.server.model.RetailerOrder;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IRetailerOrderRepository extends ReactiveMongoRepository<RetailerOrder, String> {
    @Query("{'businessid':?0, 'created':{'$gt': ?1, '$lt': ?2}}")
    Flux<RetailerOrder> findByBusinessidAndDate(final String id, final Date date1, final Date date2);
    @Query("{'businessid':?0, 'created':{'$gt': ?1, '$lt': ?2}, 'modified':{'$gt':?3} }")
    Flux<RetailerOrder> findByBusinessidAndDateInc(final String id, final Date date1, final Date date2, final Date timestamp);

    @Query("{'clientid':?0, 'created':{'$gt': ?1, '$lt': ?2}}")
    Flux<RetailerOrder> findByClientidAndDate(final String id, final Date date1, final Date date2);
    @Query("{'serial':?0}")
    Mono<RetailerOrder> findBySerial(final String serial );
    @Query(value="{'businessid': ?0, 'created':{'$gt':?1, '$lt': ?2}}",
            fields="{'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0}")
    Flux<RetailerOrder> findDayReport(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid': ?0, 'created':{'$gt':?1, '$lt': ?2}}",
            fields="{'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0}")
    Flux<RetailerOrder> findMonthReport(final String id, final Date date1, final Date date2);

    @Query(value="{'businessid': ?0, 'created':{'$gt':?1, '$lt': ?2}, 'modified':{'$gt':?3}}",
            fields="{'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0}")
    Flux<RetailerOrder> findDayReportInc(final String id, final Date date1, final Date date2, final Date timestamp);
    @Query(value="{'businessid': ?0, 'created':{'$gt':?1, '$lt': ?2}, 'modified':{'$gt':?3}}",
            fields="{'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0}")
    Flux<RetailerOrder> findMonthReportInc(final String id, final Date date1, final Date date2, final Date timestamp);


    @Query(value="{'businessid':?0, 'created':{'$gt': ?1, '$lt': ?2}}", count = true )
    Mono<Long> countRetailerOrder(final String id, final Date date1, final Date date2);
/*
    @Query(value="{'region': ?0, 'created':{'$gt':?1, '$lt': ?2}}",
            fields="{'businessid':0,'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0 ,'data':0}")
    Flux<RetailerOrderRegionReport> findRegionDayReport(final String region, final Date date1, final Date date2);
    @Query(value="{'region': ?0, 'created':{'$gt':?1, '$lt': ?2}}",
            fields="{'businessid':0,'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0 ,'data':0}")
    Flux<RetailerOrderRegionReport> findRegionMonthReport(final String region, final Date date1, final Date date2);

    @Query(value="{'region': ?0, 'created':{'$gt':?1, '$lt': ?2}, 'modified':{'$gt':?3}}",
            fields="{'businessid':0,'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0 ,'data':0}")
    Flux<RetailerOrderRegionReport> findRegionDayReportInc(final String region, final Date date1, final Date date2, final Date timestamp);
    @Query(value="{'region': ?0, 'created':{'$gt':?1, '$lt': ?2}, 'modified':{'$gt':?3}}",
            fields="{'businessid':0,'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0 ,'data':0}")
    Flux<RetailerOrderRegionReport> findRegionMonthReportInc(final String region, final Date date1, final Date date2, final Date timestamp);


 */
}