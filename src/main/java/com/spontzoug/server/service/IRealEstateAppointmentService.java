package com.spontzoug.server.service;

import com.spontzoug.server.http.AppointmentRegionReport;
import com.spontzoug.server.model.RealEstateAppointment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IRealEstateAppointmentService {
    void create(RealEstateAppointment appointment);
    Mono<RealEstateAppointment> update(RealEstateAppointment appointment);
    Mono<Void> deleteById(final String id);
    Mono<RealEstateAppointment> findById(final String id);
    Flux<RealEstateAppointment> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2);
    Flux<RealEstateAppointment> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<RealEstateAppointment> findDayReport(
            final String id, final Date date1, final Date date2);
    Flux<RealEstateAppointment> findMonthReport(
            final String id, final Date date1, final Date date2);
    Flux<RealEstateAppointment> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<RealEstateAppointment> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);

}