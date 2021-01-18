package com.spontzoug.server.service;

import com.spontzoug.server.model.Cashier;
import com.spontzoug.server.model.Cashier;
import com.spontzoug.server.repository.ICashierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class CashierService implements ICashierService {
    @Autowired
    private ICashierRepository cashierRepository;

    public void create(Cashier cashier){
        cashierRepository.save(cashier).subscribe();
    }
    public Mono<Cashier> update(Cashier cashier){
        return cashierRepository.save(cashier);
    }
    public Mono<Void> deleteById(final String id){
        return cashierRepository.deleteById(id);
    }
    public Mono<Cashier> findById(final String id){
        return cashierRepository.findById(id);
    }
    public Flux<Cashier> findByBusinessid(final String id){
        return cashierRepository.findByBusinessid(id);
    }
    public Flux<Cashier> findByBusinessidInc(final String id, final Date date){
        return cashierRepository.findByBusinessidInc(id,date);
    }
    public Flux<Cashier> findDynamics(final String id){
        return cashierRepository.findDynamics(id);
    }
    public Flux<Cashier> findDynamicsInc(final String id, final Date date){
        return cashierRepository.findDynamicsInc(id,date);
    }
}