package com.spontzoug.server.service;

import com.spontzoug.server.model.RegionCity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IRegionCityService {
    void create(RegionCity appointment);
    Mono<RegionCity> update(RegionCity appointment);
    Mono<Void> deleteById(final String id);
    Mono<RegionCity> findById(final String id);
    Flux<RegionCity> findAll();
    Flux<RegionCity> findAllInc(final Date date);
    Mono<Long> countByRegionCity(final String region, final String city);
}