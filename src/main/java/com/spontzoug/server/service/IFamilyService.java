package com.spontzoug.server.service;

import com.spontzoug.server.model.Family;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IFamilyService {
    Mono<Family> findById(final String id);
    void create (Family family);
    Mono<Family> update(Family family);
    Mono<Void> deleteById(final String id);
    Flux<Family> findByFirstName(final String firstName);
    Mono<Long> countByBusinessIdAndPhone(final String busienssid, final String phone);
    Flux<Family> findByBusinessid(final String id);
    Flux<Family> findByBusinessidInc(final String id, final Date date);
    Flux<Family> findFamilies(final String id);
    Flux<Family> findDynamics(final String id);
    Flux<Family> findDynamicsInc(final String id, final Date date);
}