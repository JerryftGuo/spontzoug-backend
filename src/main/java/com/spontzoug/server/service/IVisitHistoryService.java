package com.spontzoug.server.service;


import com.spontzoug.server.model.VisitHistory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IVisitHistoryService {
    void create(VisitHistory b);
    Mono<VisitHistory> findById(final String id);
    Mono<VisitHistory> update(VisitHistory service);
    Mono<Void> deleteById(final String id);
    Flux<VisitHistory> findByBusinessFamilyId(
            final String bid, final String fid, final String  mid);
    Flux<VisitHistory> findByBusinessFamilyIdInc(
            final String bid, final String fid, final String mid, final Date date);

}