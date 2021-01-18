package com.spontzoug.server.service;


import com.spontzoug.server.model.Notice;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface INoticeService {
    void create(Notice b);
    Mono<Notice> findById(final String id);
    Mono<Notice> update(Notice service);
    Mono<Void> deleteById(final String id);
    Flux<Notice> findByBusinessid(final String id);
    Flux<Notice> findByBusinessidInc(final String id, final Date date);
    Flux<Notice> findDynamics(final String id);
    Flux<Notice> findDynamicsInc(final String id, final Date date);
}