package com.spontzoug.server.service;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.FoodMenu;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IFoodMenuService {
    void create(FoodMenu b);
    Mono<FoodMenu> findById(final String id);
    Mono<FoodMenu> update(FoodMenu menu);
    Mono<Void> deleteById(final String id);
    Flux<FoodMenu> findByBusinessid(final String id);
    Flux<FoodMenu> findByBusinessidInc(final String id, final Date date);
    Flux<FoodMenu> findDynamics(final String id);
    Flux<FoodMenu> findDynamicsInc(final String id, final Date date);
    Flux<FoodMenu> findByRegion(final String region);
    Flux<FoodMenu> findByRegionInc(final String region, final Date date);
    Mono<Long> countActive(final String id);
    Mono<Long> countDeleting(final String id, final Date date1, final Date date2);
}