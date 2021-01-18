package com.spontzoug.server.service;

import com.spontzoug.server.http.AppointmentRegionReport;
import com.spontzoug.server.model.AutomativeSaleAppointment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomativeSaleAppointmentService {
    void create(AutomativeSaleAppointment appointment);
    Mono<AutomativeSaleAppointment> update(AutomativeSaleAppointment appointment);
    Mono<Void> deleteById(final String id);
    Mono<AutomativeSaleAppointment> findById(final String id);
    Flux<AutomativeSaleAppointment> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2);
    Flux<AutomativeSaleAppointment> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final Date timestamp);

    Flux<AutomativeSaleAppointment> findDayReport(
            final String id, final Date date1, final Date date2);
    Flux<AutomativeSaleAppointment> findMonthReport(
            final String id, final Date date1, final Date date2);

    Flux<AutomativeSaleAppointment> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<AutomativeSaleAppointment> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);

}