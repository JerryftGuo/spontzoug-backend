package com.spontzoug.server.service;

import com.spontzoug.server.model.Menu;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IMenuService {
    void create(Menu b);
    Mono<Menu> findById(final String id);
    Mono<Menu> update(Menu menu);
    Mono<Void> deleteById(final String id);
    Flux<Menu> findByBusinessid(final String id);
    Flux<Menu> findByBusinessidInc(final String id, final Date date);
    Flux<Menu> findDynamics(final String id);
    Flux<Menu> findDynamicsInc(final String id, final Date date);
}