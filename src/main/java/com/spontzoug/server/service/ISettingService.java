package com.spontzoug.server.service;


import com.spontzoug.server.model.Setting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ISettingService {
    void create(Setting b);
    Mono<Setting> findById(final String id);
    Mono<Setting> update(Setting service);
    Mono<Void> deleteById(final String id);
    Flux<Setting> findByBusinessid(final String id);
    Flux<Setting> findByBusinessidInc(final String id, final Date date);
    Flux<Setting> findDynamics(final String id);
    Flux<Setting> findDynamicsInc(final String id, final Date date);
}