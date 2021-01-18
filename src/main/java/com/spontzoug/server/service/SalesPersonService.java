package com.spontzoug.server.service;

import com.spontzoug.server.model.Practitioner;
import com.spontzoug.server.model.SalesPerson;
import com.spontzoug.server.repository.ISalesPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class SalesPersonService implements ISalesPersonService {
    @Autowired
    private ISalesPersonRepository saleRepository;

    public void create(SalesPerson sale){
        saleRepository.save(sale).subscribe();
    }
    public Mono<SalesPerson> update(SalesPerson sale){
        return saleRepository.save(sale);
    }
    public Mono<Void> deleteById(final String id){
        return saleRepository.deleteById(id);
    }
    public Mono<SalesPerson> findById(final String id){
        return saleRepository.findById(id);
    }
    public Flux<SalesPerson> findByBusinessid(final String id){
        return saleRepository.findByBusinessid(id);
    }
    public Flux<SalesPerson> findByBusinessidInc(final String id, final Date date){
        return saleRepository.findByBusinessidInc(id,date);
    }
    public Flux<SalesPerson> findDynamics(final String id){
        return saleRepository.findDynamics(id);
    }
    public Flux<SalesPerson> findDynamicsInc(final String id, final Date date){
        return saleRepository.findDynamicsInc(id,date);
    }
}