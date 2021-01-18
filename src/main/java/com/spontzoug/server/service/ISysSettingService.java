package com.spontzoug.server.service;


import com.spontzoug.server.model.SysSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ISysSettingService {
    void create(SysSetting b);
    Mono<SysSetting> findById(final String id);
    Mono<SysSetting> update(SysSetting service);
    Mono<Void> deleteById(final String id);
    Flux<SysSetting> findByBusinessid(final String id);
    Flux<SysSetting> findByBusinessidInc(final String id, final Date date);
    Flux<SysSetting> findDynamics(final String id);
    Flux<SysSetting> findDynamicsInc(final String id, final Date date);
}