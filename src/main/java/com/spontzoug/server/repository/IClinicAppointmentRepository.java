package com.spontzoug.server.repository;

import com.spontzoug.server.model.ClinicAppointment;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IClinicAppointmentRepository extends ReactiveMongoRepository<ClinicAppointment,String> {
    @Query(value="{'businessid': ?0, 'start':{'$gt':?1, '$lt': ?2}}")
    Flux<ClinicAppointment> findByBusinessidAndStartAfterAndStartBefore(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid': ?0, 'date':?1}")
    Flux<ClinicAppointment> findByBusinessidAndDate(final String id, final Long date);
    Mono<ClinicAppointment> findById(final String id);
    @Query(value="{ 'familyid':?0, 'start':{'$gt':?1}}")
    Flux<ClinicAppointment> findByFamilyidOrderByDateDesc(final String id, final Date date1);
    @Query(value="{'businessid': ?0, 'start':{'$gt':?1, '$lt': ?2}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<ClinicAppointment> findDayReport(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid': ?0, 'start':{'$gt':?1, '$lt': ?2}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'end':0,'created':0, 'modified':0, 'creator':0}")
    Flux<ClinicAppointment> findMonthReport(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid':?0, 'start':{'$gt': ?1, '$lt': ?2}}", count = true )
    Mono<Long> countClinicAppointment(final String id, final Date date1, final Date date2);

    @Query(value="{'region': ?0, 'start':{'$gt':?1, '$lt': ?2}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<ClinicAppointment> findRegionDayReport(final String region, final Date date1, final Date date2);
    @Query(value="{'region': ?0, 'start':{'$gt':?1, '$lt': ?2}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'end':0,'created':0, 'modified':0, 'creator':0}")
    Flux<ClinicAppointment> findRegionMonthReport(final String region, final Date date1, final Date date2);

    @Query(value="{'region': ?0, 'start':{'$gt':?1, '$lt': ?2},'modified':{'$gt':?3} }",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<ClinicAppointment> findRegionDayReportInc(final String region, final Date date1, final Date date2, final Date timestamp);
    @Query(value="{'region': ?0, 'start':{'$gt':?1, '$lt': ?2},'modified':{'$gt':?3}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'end':0,'created':0, 'modified':0, 'creator':0}")
    Flux<ClinicAppointment> findRegionMonthReportInc(final String region, final Date date1, final Date date2, final Date timestamp);

}