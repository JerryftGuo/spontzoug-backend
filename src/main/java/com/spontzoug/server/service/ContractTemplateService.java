package com.spontzoug.server.service;

import com.spontzoug.server.model.ContractTemplate;
import com.spontzoug.server.repository.IContractTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class ContractTemplateService implements IContractTemplateService {
    @Autowired
    private IContractTemplateRepository contractTemplateRepository;

    public void create(ContractTemplate contractTemplate) {
        contractTemplateRepository.save(contractTemplate).subscribe();
    }

    public Mono<ContractTemplate> update(ContractTemplate contractTemplate){
        return contractTemplateRepository.save(contractTemplate);
    }

    public Mono<Void> deleteById(final String id){
        return contractTemplateRepository.deleteById(id);
    }

    public Mono<ContractTemplate> findById(final String id){
        return contractTemplateRepository.findById(id);
    }
    public Mono<ContractTemplate> findProductByName(final String name) {
        return contractTemplateRepository.findProductByName(name);
    }

    public Mono<ContractTemplate> findProductIncByName(final String name, final Date date) {
        return contractTemplateRepository.findProductIncByName(name, date);
    }

    public Flux<ContractTemplate> findProductAll() {
        return contractTemplateRepository.findProductAll();
    }

    public Flux<ContractTemplate> findProductIncAll(final Date date) {
        return contractTemplateRepository.findProductIncAll(date);
    }
}