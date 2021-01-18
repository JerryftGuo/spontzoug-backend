package com.spontzoug.server.service;


import com.spontzoug.server.model.ContractTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IContractTemplateService {
    void create(ContractTemplate b);
    Mono<ContractTemplate> findById(final String id);
    Mono<ContractTemplate> update(ContractTemplate service);
    Mono<Void> deleteById(final String id);
    Mono<ContractTemplate> findProductByName(final String name);
    Mono<ContractTemplate> findProductIncByName(final String name, final Date date);
    Flux<ContractTemplate> findProductAll();
    Flux<ContractTemplate> findProductIncAll(final Date date);
}