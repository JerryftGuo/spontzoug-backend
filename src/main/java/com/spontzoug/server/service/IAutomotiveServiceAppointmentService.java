package com.spontzoug.server.service;

import com.spontzoug.server.model.AutomotiveServiceAppointment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomotiveServiceAppointmentService {
    void create(AutomotiveServiceAppointment appointment);
    Mono<AutomotiveServiceAppointment> update(AutomotiveServiceAppointment appointment);
    Mono<Void> deleteById(final String id);
    Mono<AutomotiveServiceAppointment> findById(final String id);
    Flux<AutomotiveServiceAppointment> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2);
    Flux<AutomotiveServiceAppointment> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final Date timestamp);

    Flux<AutomotiveServiceAppointment> findDayReport(
            final String id, final Date date1, final Date date2);
    Flux<AutomotiveServiceAppointment> findMonthReport(
            final String id, final Date date1, final Date date2);

    Flux<AutomotiveServiceAppointment> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<AutomotiveServiceAppointment> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);

}