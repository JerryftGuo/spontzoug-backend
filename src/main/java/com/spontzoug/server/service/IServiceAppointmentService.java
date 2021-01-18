package com.spontzoug.server.service;

import com.spontzoug.server.model.ServiceAppointment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IServiceAppointmentService {
    void create(ServiceAppointment appointment);
    Mono<ServiceAppointment> update(ServiceAppointment appointment);
    Mono<Void> deleteById(final String id);
    Mono<ServiceAppointment> findById(final String id);
    Flux<ServiceAppointment> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2);
    Flux<ServiceAppointment> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final Date timestamp);

    Flux<ServiceAppointment> findDayReport(
            final String id, final Date date1, final Date date2);
    Flux<ServiceAppointment> findMonthReport(
            final String id, final Date date1, final Date date2);

    Flux<ServiceAppointment> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<ServiceAppointment> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
/*
    Flux<ServiceAppointmentRegionReport> findRegionDayReport(
            final String region, final Date date1, final Date date2);
    Flux<ServiceAppointmentRegionReport> findRegionMonthReport(
            final String region, final Date date1, final Date date2);

    Flux<ServiceAppointmentRegionReport> findRegionDayReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp);
    Flux<ServiceAppointmentRegionReport> findRegionMonthReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp);
*/
}