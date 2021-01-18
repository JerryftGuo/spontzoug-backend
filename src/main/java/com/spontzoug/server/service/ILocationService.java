package com.spontzoug.server.service;

import com.spontzoug.server.model.Location;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ILocationService {
    void create(Location b);
    Mono<Location> update(Location business);
    Mono<Void> deleteById(final String id);
    Mono<Location> findById(final String id);
    Flux<Location> findByBusinessid(final String id);
    Flux<Location> findByBusinessidInc(final String id, final Date date);
    Flux<Location> findByGeolocation(final Point point, final Distance distance);
    Flux<Location> findDynamics(final String id);
    Flux<Location> findDynamicsInc(final String id, final Date date);
}