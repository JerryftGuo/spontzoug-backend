package com.spontzoug.server.service;


import com.spontzoug.server.model.Invoice;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IInvoiceService {
    void create(Invoice b);
    Mono<Invoice> findById(final String id);
    Mono<Invoice> update(Invoice service);
    Mono<Void> deleteById(final String id);
    Flux<Invoice> findByBusinessidAndDate(final String id, final Date date1, final Date date2);
    Flux<Invoice> findByClientidAndDate(final String id, final Date date1, final Date date2);
    Mono<Invoice> findByAppointmentid(final String id);

}