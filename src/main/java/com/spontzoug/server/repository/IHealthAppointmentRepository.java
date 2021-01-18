package com.spontzoug.server.repository;

import com.spontzoug.server.model.HealthAppointment;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IHealthAppointmentRepository extends ReactiveMongoRepository<HealthAppointment,String> {
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2}}")
    Flux<HealthAppointment> findBusinessidAndDate(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2},'modified':{'$gt':?3} }")
    Flux<HealthAppointment> findBusinessidAndDateInc(final String id, final Date date1, final Date date2,final Date timestamp);

    Mono<HealthAppointment> findById(final String id);
    @Query(value="{ 'familyid':?0, 'start':{'$gt':?1}}")
    Flux<HealthAppointment> findByFamilyidOrderByDateDesc(final String id, final Date date1);
    @Query(value="{'businessid': ?0, 'start':{'$gt':?1, '$lt': ?2}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<HealthAppointment> findDayReport(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'end':0,'created':0, 'modified':0, 'creator':0}")
    Flux<HealthAppointment> findMonthReport(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid':?0, 'start':{'$gte': ?1, '$lt': ?2}}", count = true )
    Mono<Long> countHealthAppointment(final String id, final Date date1, final Date date2);

    @Query(value="{'businessid':?0, 'familyid':?1, 'memberid':?2, 'service':?3, 'start':{'$gte': ?4, '$lt': ?5}}")
    Flux<HealthAppointment> findByBusinessFamilyMemberServiceDate(
            final String busienssid, final String familyid, final String memberid, final String service,
            final Date date1, final Date date2);

    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2},'modified':{'$gt':?3} }",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<HealthAppointment> findDayReportInc(final String id, final Date date1, final Date date2, final Date timestamp);
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2},'modified':{'$gt':?3} }",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'end':0,'created':0, 'modified':0, 'creator':0}")
    Flux<HealthAppointment> findMonthReportInc(final String id, final Date date1, final Date date2, final Date timestamp);
/*
    @Query(value="{'region': ?0, 'start':{'$gt':?1, '$lt': ?2}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<HealthAppointmentRegionReport> findRegionDayReport(final String region, final Date date1, final Date date2);
    @Query(value="{'region': ?0, 'start':{'$gt':?1, '$lt': ?2}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'end':0,'created':0, 'modified':0, 'creator':0}")
    Flux<HealthAppointmentRegionReport> findRegionMonthReport(final String region, final Date date1, final Date date2);

    @Query(value="{'region': ?0, 'start':{'$gt':?1, '$lt': ?2},'modified':{'$gt':?3} }",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<HealthAppointmentRegionReport> findRegionDayReportInc(final String region, final Date date1, final Date date2, final Date timestamp);
    @Query(value="{'region': ?0, 'start':{'$gt':?1, '$lt': ?2},'modified':{'$gt':?3}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'end':0,'created':0, 'modified':0, 'creator':0}")
    Flux<HealthAppointmentRegionReport> findRegionMonthReportInc(final String region, final Date date1, final Date date2, final Date timestamp);


 */
}