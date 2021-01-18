package com.spontzoug.server.repository;

import com.spontzoug.server.model.Reservation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IReservationRepository extends ReactiveMongoRepository<Reservation,String> {
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2}}")
    Flux<Reservation> findBusinessidAndDate(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2},'modified':{'$gt':?3} }")
    Flux<Reservation> findBusinessidAndDateInc(final String id, final Date date1, final Date date2,final Date timestamp);

}