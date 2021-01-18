package com.spontzoug.server.service;

import com.spontzoug.server.model.Realtor;
import com.spontzoug.server.repository.IRealtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class RealtorService implements IRealtorService {
    @Autowired
    private IRealtorRepository realtorRepository;

    public void create(Realtor realtor){
        realtorRepository.save(realtor).subscribe();
    }
    public Mono<Realtor> update(Realtor realtor){
        return realtorRepository.save(realtor);
    }
    public Mono<Void> deleteById(final String id){
        return realtorRepository.deleteById(id);
    }
    public Mono<Realtor> findById(final String id){
        return realtorRepository.findById(id);
    }
    public Flux<Realtor> findByBusinessid(final String id){
        return realtorRepository.findByBusinessid(id);
    }
    public Flux<Realtor> findByBusinessidInc(final String id, final Date date){
        return realtorRepository.findByBusinessidInc(id,date);
    }
}