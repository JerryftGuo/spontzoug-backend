package com.spontzoug.server.repository;

import com.spontzoug.server.model.AutomotiveSaleAppointment;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomotiveSaleAppointmentRepository extends ReactiveMongoRepository<AutomotiveSaleAppointment,String> {
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2}}")
    Flux<AutomotiveSaleAppointment> findBusinessidAndDate(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2},'modified':{'$gt':?3} }")
    Flux<AutomotiveSaleAppointment> findBusinessidAndDateInc(final String id, final Date date1, final Date date2,final Date timestamp);

    @Query(value="{'businessid': ?0, 'start':{'$gt':?1, '$lt': ?2}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<AutomotiveSaleAppointment> findDayReport(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'end':0,'created':0, 'modified':0, 'creator':0}")
    Flux<AutomotiveSaleAppointment> findMonthReport(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid':?0, 'start':{'$gte': ?1, '$lt': ?2}}", count = true )
    Mono<Long> countAutomotiveSaleAppointment(final String id, final Date date1, final Date date2);

    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2},'modified':{'$gt':?3} }",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<AutomotiveSaleAppointment> findDayReportInc(final String id, final Date date1, final Date date2, final Date timestamp);
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2},'modified':{'$gt':?3} }",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'end':0,'created':0, 'modified':0, 'creator':0}")
    Flux<AutomotiveSaleAppointment> findMonthReportInc(final String id, final Date date1, final Date date2, final Date timestamp);

}