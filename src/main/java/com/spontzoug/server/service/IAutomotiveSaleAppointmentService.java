package com.spontzoug.server.service;

import com.spontzoug.server.http.AppointmentRegionReport;
import com.spontzoug.server.model.AutomotiveSaleAppointment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomotiveSaleAppointmentService {
    void create(AutomotiveSaleAppointment appointment);
    Mono<AutomotiveSaleAppointment> update(AutomotiveSaleAppointment appointment);
    Mono<Void> deleteById(final String id);
    Mono<AutomotiveSaleAppointment> findById(final String id);
    Flux<AutomotiveSaleAppointment> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2);
    Flux<AutomotiveSaleAppointment> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final Date timestamp);

    Flux<AutomotiveSaleAppointment> findDayReport(
            final String id, final Date date1, final Date date2);
    Flux<AutomotiveSaleAppointment> findMonthReport(
            final String id, final Date date1, final Date date2);

    Flux<AutomotiveSaleAppointment> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<AutomotiveSaleAppointment> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);

}