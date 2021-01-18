package com.spontzoug.server.service;

import com.spontzoug.server.http.AppointmentRegionReport;
import com.spontzoug.server.model.FinancialInsuranceAppointment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IFinancialInsuranceAppointmentService {
    void create(FinancialInsuranceAppointment appointment);
    Mono<FinancialInsuranceAppointment> update(FinancialInsuranceAppointment appointment);
    Mono<Void> deleteById(final String id);
    Mono<FinancialInsuranceAppointment> findById(final String id);
    Flux<FinancialInsuranceAppointment> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2);
    Flux<FinancialInsuranceAppointment> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<FinancialInsuranceAppointment> findDayReport(
            final String id, final Date date1, final Date date2);
    Flux<FinancialInsuranceAppointment> findMonthReport(
            final String id, final Date date1, final Date date2);
    Flux<FinancialInsuranceAppointment> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<FinancialInsuranceAppointment> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);

}