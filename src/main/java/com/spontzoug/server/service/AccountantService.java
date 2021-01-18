package com.spontzoug.server.service;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.repository.IAccountantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class AccountantService implements IAccountantService {
    @Autowired
    private IAccountantRepository accountantRepository;

    public void create(Accountant accountant)
    {
        accountantRepository.save(accountant).subscribe();
    }
    public Mono<Accountant> update(Accountant accountant)
    {
        return accountantRepository.save(accountant);
    }
    public Mono<Void> deleteById(final String id){
        return accountantRepository.deleteById(id);
    }
    public Mono<Accountant> findById(final String id)
    {
        return accountantRepository.findById(id);
    }
    public Flux<Accountant> findByBusinessid(final String id)
    {
        return accountantRepository.findByBusinessid(id);
    }
    public Flux<Accountant> findByBusinessidInc(final String id, final Date date)
    {
        return accountantRepository.findByBusinessidInc(id,date);
    }
}