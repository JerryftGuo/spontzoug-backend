package com.spontzoug.server.service;

import com.spontzoug.server.http.AppointmentRegionReport;
import com.spontzoug.server.model.PersonalCareAppointment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IPersonalCareAppointmentService {
    void create(PersonalCareAppointment appointment);
    Mono<PersonalCareAppointment> update(PersonalCareAppointment appointment);
    Mono<Void> deleteById(final String id);
    Mono<PersonalCareAppointment> findById(final String id);
    Flux<PersonalCareAppointment> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2);
    Flux<PersonalCareAppointment> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final Date timestamp);

    Flux<PersonalCareAppointment> findDayReport(
            final String id, final Date date1, final Date date2);
    Flux<PersonalCareAppointment> findMonthReport(
            final String id, final Date date1, final Date date2);

    Flux<PersonalCareAppointment> findDayReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);
    Flux<PersonalCareAppointment> findMonthReportInc(
            final String id, final Date date1, final Date date2, final Date timestamp);

}