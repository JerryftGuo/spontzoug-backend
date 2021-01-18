package com.spontzoug.server.service;

import com.spontzoug.server.model.MenuCat;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IMenuCatService {
    void create(MenuCat m);
    Mono<MenuCat> findById(final String id);
    Mono<MenuCat> update(MenuCat m);
    Mono<Void> deleteById(final String id);
    Flux<MenuCat> findByBusinessid(final String id);
    Flux<MenuCat> findByBusinessidInc(final String id, final Date date);
    Flux<MenuCat> findDynamics(final String id);
    Flux<MenuCat> findDynamicsInc(final String id, final Date date);
}