package com.spontzoug.server.service;

import com.spontzoug.server.model.HealthAppointment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IHealthAppointmentService {
    void create(HealthAppointment appointment);
    Mono<HealthAppointment> update(HealthAppointment appointment);
    Mono<Void> deleteById(final String id);
    Mono<HealthAppointment> findById(final String id);
    Flux<HealthAppointment> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2);
    Flux<HealthAppointment> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final Date timestamp);

    Flux<HealthAppointment> findByBusinessFamilyMemberServiceDate (
            final  String businessid, final String familyid, final String memeberid,
            final String service, final Date date1, final Date date2 );

    Flux<HealthAppointment> findByFamilyidOrderByDateDesc(
            final String id, final Date date1);
    Flux<HealthAppointment> findDayReport(
            final String id, final Date date1, final Date date2);
    Flux<HealthAppointment> findMonthReport(
            final String id, final Date date1, final Date date2);

    Flux<HealthAppointment> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<HealthAppointment> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
/*
    Flux<HealthAppointmentRegionReport> findRegionDayReport(
            final String region, final Date date1, final Date date2);
    Flux<HealthAppointmentRegionReport> findRegionMonthReport(
            final String region, final Date date1, final Date date2);

    Flux<HealthAppointmentRegionReport> findRegionDayReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp);
    Flux<HealthAppointmentRegionReport> findRegionMonthReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp);
*/
}