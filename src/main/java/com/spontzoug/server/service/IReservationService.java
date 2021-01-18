package com.spontzoug.server.service;

import com.spontzoug.server.http.AppointmentRegionReport;
import com.spontzoug.server.model.Reservation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IReservationService {
    void create(Reservation appointment);
    Mono<Reservation> update(Reservation appointment);
    Mono<Void> deleteById(final String id);
    Mono<Reservation> findById(final String id);
    Flux<Reservation> findByBusinessidAndDate(
            final String id, final Date date1, final Date date2);
    Flux<Reservation> findByBusinessidAndDateInc(
            final String id, final Date date1, final Date date2, final Date timestamp);

}