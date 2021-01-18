package com.spontzoug.server.service;

import com.spontzoug.server.model.Table;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ITableService {
    void create(Table b);
    Mono<Table> findById(final String id);
    Mono<Table> update(Table room);
    Mono<Void> deleteById(final String id);
    Flux<Table> findByBusinessid(final String id);
    Flux<Table> findByBusinessidInc(final String id,final Date date);
    Flux<Table> findDynamics(final String id);
    Flux<Table> findDynamicsInc(final String id, final Date date);
}