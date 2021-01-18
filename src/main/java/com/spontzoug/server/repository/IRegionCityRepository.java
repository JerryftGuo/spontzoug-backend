package com.spontzoug.server.repository;

import com.spontzoug.server.model.RegionCity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IRegionCityRepository extends ReactiveMongoRepository<RegionCity,String> {
    @Query("{}")
    Flux<RegionCity> findAll();
    @Query(value="{'modified':{'$gte':?0}}")
    Flux<RegionCity> findAllInc(final Date date);
    @Query(value="{'region':?0,'city':?1}", count = true)
    Mono<Long> countByRegionCity(final String region, final String city);
}