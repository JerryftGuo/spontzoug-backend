package com.spontzoug.server.service;

import com.spontzoug.server.model.VisitHistory;
import com.spontzoug.server.repository.IVisitHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class VisitHistoryService implements IVisitHistoryService {
    @Autowired
    private IVisitHistoryRepository visitHistoryRepository;

    public void create(VisitHistory visitHistory) {
        visitHistoryRepository.save(visitHistory).subscribe();
    }

    public Mono<VisitHistory> update(VisitHistory visitHistory){
        return visitHistoryRepository.save(visitHistory);
    }

    public Mono<Void> deleteById(final String id){
        return visitHistoryRepository.deleteById(id);
    }

    public Mono<VisitHistory> findById(final String id){
        return visitHistoryRepository.findById(id);
    }
    public Flux<VisitHistory> findByBusinessFamilyId(
            final String bid, final String fid, final String mid) {
        return visitHistoryRepository.findByBusinessFamilyId(bid, fid, mid);
    }
    public Flux<VisitHistory> findByBusinessFamilyIdInc(
            final String bid, final String fid, final String mid, final Date date) {
        return visitHistoryRepository.findByBusinessFamilyIdInc(bid, fid, mid, date);
    }

}