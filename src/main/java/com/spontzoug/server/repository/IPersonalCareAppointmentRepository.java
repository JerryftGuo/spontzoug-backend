package com.spontzoug.server.repository;

import com.spontzoug.server.model.PersonalCareAppointment;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IPersonalCareAppointmentRepository extends ReactiveMongoRepository<PersonalCareAppointment,String> {
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2}}")
    Flux<PersonalCareAppointment> findBusinessidAndDate(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2},'modified':{'$gt':?3} }")
    Flux<PersonalCareAppointment> findBusinessidAndDateInc(final String id, final Date date1, final Date date2,final Date timestamp);

    @Query(value="{'businessid': ?0, 'start':{'$gt':?1, '$lt': ?2}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<PersonalCareAppointment> findDayReport(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'end':0,'created':0, 'modified':0, 'creator':0}")
    Flux<PersonalCareAppointment> findMonthReport(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid':?0, 'start':{'$gte': ?1, '$lt': ?2}}", count = true )
    Mono<Long> countPersonalCareAppointment(final String id, final Date date1, final Date date2);

    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2},'modified':{'$gt':?3} }",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<PersonalCareAppointment> findDayReportInc(final String id, final Date date1, final Date date2, final Date timestamp);
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2},'modified':{'$gt':?3} }",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'end':0,'created':0, 'modified':0, 'creator':0}")
    Flux<PersonalCareAppointment> findMonthReportInc(final String id, final Date date1, final Date date2, final Date timestamp);

}