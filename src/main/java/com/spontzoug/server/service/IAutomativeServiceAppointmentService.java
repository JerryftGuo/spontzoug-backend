package com.spontzoug.server.service;

import com.spontzoug.server.http.AppointmentRegionReport;
import com.spontzoug.server.model.AutomativeServiceAppointment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IAutomativeServiceAppointmentService {
    void create(AutomativeServiceAppointment appointment);
    Mono<AutomativeServiceAppointment> update(AutomativeServiceAppointment appointment);
    Mono<Void> deleteById(final String id);
    Mono<AutomativeServiceAppointment> findById(final String id);
    Flux<AutomativeServiceAppointment> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2);
    Flux<AutomativeServiceAppointment> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final Date timestamp);

    Flux<AutomativeServiceAppointment> findDayReport(
            final String id, final Date date1, final Date date2);
    Flux<AutomativeServiceAppointment> findMonthReport(
            final String id, final Date date1, final Date date2);

    Flux<AutomativeServiceAppointment> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<AutomativeServiceAppointment> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);

}