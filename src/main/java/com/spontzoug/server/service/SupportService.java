package com.spontzoug.server.service;

import com.spontzoug.server.model.Support;
import com.spontzoug.server.model.Support;
import com.spontzoug.server.repository.ISupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class SupportService implements ISupportService {
    @Autowired
    private ISupportRepository supportRepository;

    public void create(Support support){
        supportRepository.save(support).subscribe();
    }
    public Mono<Support> update(Support support){
        return supportRepository.save(support);
    }
    public Mono<Void> deleteById(final String id){
        return supportRepository.deleteById(id);
    }
    public Mono<Support> findById(final String id){
        return supportRepository.findById(id);
    }
    public Flux<Support> findByBusinessid(final String id){
        return supportRepository.findByBusinessid(id);
    }
    public Flux<Support> findByBusinessidInc(final String id, final Date date){
        return supportRepository.findByBusinessidInc(id,date);
    }
    public Flux<Support> findDynamics(final String id){
        return supportRepository.findDynamics(id);
    }
    public Flux<Support> findDynamicsInc(final String id, final Date date){
        return supportRepository.findDynamicsInc(id,date);
    }
}