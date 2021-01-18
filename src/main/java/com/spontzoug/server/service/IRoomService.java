package com.spontzoug.server.service;

import com.spontzoug.server.model.Room;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IRoomService {
    void create(Room b);
    Mono<Room> findById(final String id);
    Mono<Room> update(Room room);
    Mono<Void> deleteById(final String id);
    Flux<Room> findByBusinessid(final String id);
    Flux<Room> findByBusinessidInc(final String id,final Date date);
    Flux<Room> findDynamics(final String id);
    Flux<Room> findDynamicsInc(final String id, final Date date);
}