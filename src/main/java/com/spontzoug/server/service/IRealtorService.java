package com.spontzoug.server.service;

import com.spontzoug.server.model.Realtor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IRealtorService {
    void create(Realtor b);
    Mono<Realtor> findById(final String id);
    Mono<Realtor> update(Realtor receptionist);
    Mono<Void> deleteById(final String id);
    Flux<Realtor> findByBusinessid(final String id);
    Flux<Realtor> findByBusinessidInc(final String id, final Date date);
}