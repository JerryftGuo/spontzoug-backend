package com.spontzoug.server.service;

import com.spontzoug.server.model.FoodCate;
import com.spontzoug.server.model.FoodMenu;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IFoodCateService {
    void create(FoodCate m);
    Mono<FoodCate> findById(final String id);
    Mono<FoodCate> update(FoodCate m);
    Mono<Void> deleteById(final String id);
    Flux<FoodCate> findByBusinessid(final String id);
    Flux<FoodCate> findByBusinessidInc(final String id, final Date date);
    Flux<FoodCate> findDynamics(final String id);
    Flux<FoodCate> findDynamicsInc(final String id, final Date date);
    Flux<FoodCate> findByRegion(final String region);
    Flux<FoodCate> findByRegionInc(final String region, final Date date);
}