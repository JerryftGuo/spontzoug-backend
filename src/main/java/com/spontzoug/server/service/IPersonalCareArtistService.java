package com.spontzoug.server.service;

import com.spontzoug.server.model.PersonalCareArtist;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IPersonalCareArtistService {
    void create(PersonalCareArtist b) ;
    Mono<PersonalCareArtist> findById(final String id);
    Mono<PersonalCareArtist> update(PersonalCareArtist practitioner);
    Mono<Void> deleteById(final String id);
    Flux<PersonalCareArtist> findByBusinessid(final String id);
    Flux<PersonalCareArtist> findByBusinessidInc(final String id, final Date date);
    Flux<PersonalCareArtist> findDynamics(final String id);
    Flux<PersonalCareArtist> findDynamicsInc(final String id, final Date date);
}