package com.spontzoug.server.service;

import com.spontzoug.server.model.ClinicAppointment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IClinicAppointmentService {
    void create(ClinicAppointment appointment);
    Mono<ClinicAppointment> update(ClinicAppointment appointment);
    Mono<Void> deleteById(final String id);
    Mono<ClinicAppointment> findById(final String id);
    Flux<ClinicAppointment> findByBusinessidAndStartAfterAndStartBefore(
            final String id, final Date date1, final Date date2);
    Flux<ClinicAppointment> findByBusinessidAndDate(
            final String id, final Long date);
    Flux<ClinicAppointment> findByFamilyidOrderByDateDesc(
            final String id, final Date date1);
    Flux<ClinicAppointment> findDayReport(
            final String id, final Date date1, final Date date2);
    Flux<ClinicAppointment> findMonthReport(
            final String id, final Date date1, final Date date2);
}