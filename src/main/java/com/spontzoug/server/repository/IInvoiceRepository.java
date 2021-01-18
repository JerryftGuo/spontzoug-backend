package com.spontzoug.server.repository;

import com.spontzoug.server.model.Invoice;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IInvoiceRepository extends ReactiveMongoRepository<Invoice, String> {
    @Query("{'businessid':?0, 'created':{'$gt': ?1, '$lt': ?2}")
    Flux<Invoice> findByBusinessidAndDate(final String id, final Date date1, final Date date2);
    @Query("{'clientid':?0, 'created':{'$gt': ?1, '$lt': ?2}")
    Flux<Invoice> findByClientidAndDate(final String id, final Date date1, final Date date2);
    @Query("{'appointmentid':?0}")
    Mono<Invoice> findByAppointmentid(final String id );
}