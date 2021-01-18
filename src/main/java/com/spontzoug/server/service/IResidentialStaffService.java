package com.spontzoug.server.service;

import com.spontzoug.server.model.ResidentialStaff;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IResidentialStaffService {
    void create(ResidentialStaff b) ;
    Mono<ResidentialStaff> findById(final String id);
    Mono<ResidentialStaff> update(ResidentialStaff practitioner);
    Mono<Void> deleteById(final String id);
    Flux<ResidentialStaff> findByBusinessid(final String id);
    Flux<ResidentialStaff> findByBusinessidInc(final String id, final Date date);
    Flux<ResidentialStaff> findDynamics(final String id);
    Flux<ResidentialStaff> findDynamicsInc(final String id, final Date date);
}