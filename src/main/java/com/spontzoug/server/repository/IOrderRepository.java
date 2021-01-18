package com.spontzoug.server.repository;

import com.spontzoug.server.model.Order;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IOrderRepository extends ReactiveMongoRepository<Order, String> {
    @Query("{'businessid':?0, 'created':{'$gt': ?1, '$lt': ?2}}")
    Flux<Order> findByBusinessidAndDate(final String id, final Date date1, final Date date2);
    @Query("{'clientid':?0, 'created':{'$gt': ?1, '$lt': ?2}}")
    Flux<Order> findByClientidAndDate(final String id, final Date date1, final Date date2);
    @Query("{'serial':?0}")
    Mono<Order> findBySerial(final String serial );
    @Query(value="{'businessid': ?0, 'created':{'$gt':?1, '$lt': ?2}}",
            fields="{'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0}")
    Flux<Order> findDayReport(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid': ?0, 'created':{'$gt':?1, '$lt': ?2}}",
            fields="{'clientid':0, 'serial':0, 'confirmation':0, 'modified':0, 'creator':0}")
    Flux<Order> findMonthReport(final String id, final Date date1, final Date date2);

    @Query(value="{'businessid':?0, 'created':{'$gt': ?1, '$lt': ?2}}", count = true )
    Mono<Long> countOrder(final String id, final Date date1, final Date date2);

}