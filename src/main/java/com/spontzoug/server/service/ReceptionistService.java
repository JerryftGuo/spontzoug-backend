package com.spontzoug.server.service;

import com.spontzoug.server.model.Receptionist;
import com.spontzoug.server.repository.IReceptionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class ReceptionistService implements IReceptionistService {
    @Autowired
    private IReceptionistRepository receptionistRepository;

    public void create(Receptionist receptionist){
        receptionistRepository.save(receptionist).subscribe();
    }
    public Mono<Receptionist> update(Receptionist receptionist){
        return receptionistRepository.save(receptionist);
    }
    public Mono<Void> deleteById(final String id){
        return receptionistRepository.deleteById(id);
    }
    public Mono<Receptionist> findById(final String id){
        return receptionistRepository.findById(id);
    }
    public Flux<Receptionist> findByBusinessid(final String id){
        return receptionistRepository.findByBusinessid(id);
    }
    public Flux<Receptionist> findByBusinessidInc(final String id, final Date date){
        return receptionistRepository.findByBusinessidInc(id,date);
    }
    public Flux<Receptionist> findDynamics(final String id){
        return receptionistRepository.findDynamics(id);
    }
    public Flux<Receptionist> findDynamicsInc(final String id, final Date date){
        return receptionistRepository.findDynamicsInc(id,date);
    }
}