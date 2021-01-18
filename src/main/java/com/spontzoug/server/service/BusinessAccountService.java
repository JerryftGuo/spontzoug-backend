package com.spontzoug.server.service;

import com.spontzoug.server.model.BusinessAccount;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IBusinessAccountRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class BusinessAccountService implements IBusinessAccountService {
    @Autowired
    private IBusinessAccountRepository businessRep;

    public void  create(BusinessAccount b){
        businessRep.save(b).subscribe();
    }
    public Mono<BusinessAccount> update(BusinessAccount b) {
        return businessRep.save(b);
    }

    public Mono<Void> deleteById(String id) {
        return businessRep.deleteById(id);
    }

    public Mono<BusinessAccount> findById(final  String id){
        return businessRep.findById(id);
    }

    public Flux<BusinessAccount> findByRegion(final String region) {
        return businessRep.findByRegion(region);
    }

    public Flux<BusinessAccount> findByRegionInc(final String region, final Date date) {
        return businessRep.findByRegionInc(region,date);
    }
    public Flux<BusinessAccount> findAllInc(final Date date) {
        return businessRep.findAllInc(date);
    }

    public Flux<BusinessAccount> findByCreator(final String creator){
        return businessRep.findByCreator(creator);
    }

    public Flux<BusinessAccount> findAll(){
        return businessRep.findAll();
    }
    public Flux<BusinessAccount> findDynamics(final  String id){
        return businessRep.findDynamics(id);
    }
    public Flux<BusinessAccount> findDynamicsInc(final  String id, final Date date){
        return businessRep.findDynamicsInc(id,date);
    }
    public Flux<BusinessAccount> findByBusinessid(final String id)
    {
        return businessRep.findByBusinessid(id);
    }
    public Flux<BusinessAccount> findByBusinessidInc(final String id,final Date date)
    {
        return businessRep.findByBusinessidInc(id,date);
    }

}