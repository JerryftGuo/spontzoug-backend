package com.spontzoug.server.service;


import com.spontzoug.server.model.Config;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IConfigService {
    void create(Config b);
    Mono<Config> findById(final String id);
    Mono<Config> update(Config service);
    Mono<Void> deleteById(final String id);
    Mono<Config> findProductByName(final String name);
    Mono<Config> findBetaByName(final String name);
    Mono<Config> findProductIncByName(final String name, final Date date);
    Mono<Config> findBetaIncByName(final String name, final Date date);
    Flux<Config> findIndustryByName(final String name);
    Flux<Config> findIndustryAll();
    Flux<Config> findIndustryIncByName(final String name, final Date date);
    Flux<Config> findIndustryIncAll(final Date date);
}