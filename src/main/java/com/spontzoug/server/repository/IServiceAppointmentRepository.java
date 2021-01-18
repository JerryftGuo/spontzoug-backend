package com.spontzoug.server.repository;

import com.spontzoug.server.model.ServiceAppointment;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IServiceAppointmentRepository extends ReactiveMongoRepository<ServiceAppointment,String> {
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2}}")
    Flux<ServiceAppointment> findBusinessidAndDate(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2},'modified':{'$gt':?3} }")
    Flux<ServiceAppointment> findBusinessidAndDateInc(final String id, final Date date1, final Date date2,final Date timestamp);

    Mono<ServiceAppointment> findById(final String id);
    @Query(value="{'businessid': ?0, 'start':{'$gt':?1, '$lt': ?2}}",
            fields="{'client':0, 'note':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<ServiceAppointment> findDayReport(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2}}",
            fields="{'client':0, 'note':0, 'end':0,'created':0, 'modified':0, 'creator':0}")
    Flux<ServiceAppointment> findMonthReport(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid':?0, 'start':{'$gte': ?1, '$lt': ?2}}", count = true )
    Mono<Long> countServiceAppointment(final String id, final Date date1, final Date date2);

    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2},'modified':{'$gt':?3} }",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<ServiceAppointment> findDayReportInc(final String id, final Date date1, final Date date2, final Date timestamp);
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2},'modified':{'$gt':?3} }",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'end':0,'created':0, 'modified':0, 'creator':0}")
    Flux<ServiceAppointment> findMonthReportInc(final String id, final Date date1, final Date date2, final Date timestamp);
/*
    @Query(value="{'region': ?0, 'start':{'$gt':?1, '$lt': ?2}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<ServiceAppointmentRegionReport> findRegionDayReport(final String region, final Date date1, final Date date2);
    @Query(value="{'region': ?0, 'start':{'$gt':?1, '$lt': ?2}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'end':0,'created':0, 'modified':0, 'creator':0}")
    Flux<ServiceAppointmentRegionReport> findRegionMonthReport(final String region, final Date date1, final Date date2);

    @Query(value="{'region': ?0, 'start':{'$gt':?1, '$lt': ?2},'modified':{'$gt':?3} }",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<ServiceAppointmentRegionReport> findRegionDayReportInc(final String region, final Date date1, final Date date2, final Date timestamp);
    @Query(value="{'region': ?0, 'start':{'$gt':?1, '$lt': ?2},'modified':{'$gt':?3}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'end':0,'created':0, 'modified':0, 'creator':0}")
    Flux<ServiceAppointmentRegionReport> findRegionMonthReportInc(final String region, final Date date1, final Date date2, final Date timestamp);


 */
}