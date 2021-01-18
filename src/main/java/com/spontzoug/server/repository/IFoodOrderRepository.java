package com.spontzoug.server.repository;

import com.spontzoug.server.http.FoodOrderRegionReport;
import com.spontzoug.server.model.FoodOrder;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IFoodOrderRepository extends ReactiveMongoRepository<FoodOrder, String> {
    @Query("{'businessid':?0, 'created':{'$gt': ?1, '$lt': ?2}}")
    Flux<FoodOrder> findByBusinessidAndDate(final String id, final Date date1, final Date date2);
    @Query("{'businessid':?0, 'created':{'$gt': ?1, '$lt': ?2}, 'modified':{'$gt':?3} }")
    Flux<FoodOrder> findByBusinessidAndDateInc(final String id, final Date date1, final Date date2, final Date timestamp);
    @Query("{'clientid':?0, 'created':{'$gt': ?1, '$lt': ?2}}")
    Flux<FoodOrder> findByClientidAndDate(final String id, final Date date1, final Date date2);
    @Query("{'serial':?0}")
    Mono<FoodOrder> findBySerial(final String serial );
    @Query(value="{'businessid': ?0, 'created':{'$gt':?1, '$lt': ?2}}",
            fields="{'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0}")
    Flux<FoodOrder> findDayReport(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid': ?0, 'created':{'$gt':?1, '$lt': ?2}}",
            fields="{'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0}")
    Flux<FoodOrder> findMonthReport(final String id, final Date date1, final Date date2);

    @Query(value="{'businessid': ?0, 'created':{'$gt':?1, '$lt': ?2}, 'modified':{'$gt':?3}}",
            fields="{'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0}")
    Flux<FoodOrder> findDayReportInc(final String id, final Date date1, final Date date2, final Date timestamp);
    @Query(value="{'businessid': ?0, 'created':{'$gt':?1, '$lt': ?2}, 'modified':{'$gt':?3}}",
            fields="{'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0}")
    Flux<FoodOrder> findMonthReportInc(final String id, final Date date1, final Date date2, final Date timestamp);

    @Query(value="{'businessid':?0, 'created':{'$gt': ?1, '$lt': ?2}}", count = true )
    Mono<Long> countFoodOrder(final String id, final Date date1, final Date date2);
/*
    @Query(value="{'region': ?0, 'created':{'$gt':?1, '$lt': ?2}}",
            fields="{'businessid':0,'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0 ,'data':0}")
    Flux<FoodOrderRegionReport> findRegionDayReport(final String region, final Date date1, final Date date2);
    @Query(value="{'region': ?0, 'created':{'$gt':?1, '$lt': ?2}}",
            fields="{'businessid':0,'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0 ,'data':0}")
    Flux<FoodOrderRegionReport> findRegionMonthReport(final String region, final Date date1, final Date date2);

    @Query(value="{'region': ?0, 'created':{'$gt':?1, '$lt': ?2}, 'modified':{'$gt':?3}}",
            fields="{'businessid':0,'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0 ,'data':0}")
    Flux<FoodOrderRegionReport> findRegionDayReportInc(final String region, final Date date1, final Date date2, final Date timestamp);
    @Query(value="{'region': ?0, 'created':{'$gt':?1, '$lt': ?2}, 'modified':{'$gt':?3}}",
            fields="{'businessid':0,'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0 ,'data':0}")
    Flux<FoodOrderRegionReport> findRegionMonthReportInc(final String region, final Date date1, final Date date2, final Date timestamp);
*/
}
