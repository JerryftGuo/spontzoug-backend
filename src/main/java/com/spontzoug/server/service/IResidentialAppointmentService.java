package com.spontzoug.server.service;

import com.spontzoug.server.http.AppointmentRegionReport;
import com.spontzoug.server.model.ResidentialAppointment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IResidentialAppointmentService {
    void create(ResidentialAppointment appointment);
    Mono<ResidentialAppointment> update(ResidentialAppointment appointment);
    Mono<Void> deleteById(final String id);
    Mono<ResidentialAppointment> findById(final String id);
    Flux<ResidentialAppointment> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2);
    Flux<ResidentialAppointment> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<ResidentialAppointment> findDayReport(
            final String id, final Date date1, final Date date2);
    Flux<ResidentialAppointment> findMonthReport(
            final String id, final Date date1, final Date date2);
    Flux<ResidentialAppointment> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<ResidentialAppointment> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);

}