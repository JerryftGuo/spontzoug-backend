package com.spontzoug.server.repository;

import com.spontzoug.server.model.BusinessAccount;
import com.spontzoug.server.model.ContractTemplate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IContractTemplateRepository extends ReactiveMongoRepository<ContractTemplate, String> {
    @Query("{'$or':[{'name':?0},{'name':'all'}],'release':'prod'}")
    Mono<ContractTemplate> findProductByName(final String name);
    @Query("{ '$or':[{'name':?0},{'name':'all'}],'release':'prod',modified:{'$gt':?1}}")
    Mono<ContractTemplate> findProductIncByName(final String name, final Date date);

    @Query("{'release':'prod'}")
    Flux<ContractTemplate> findProductAll();
    @Query("{'release':'prod',modified:{'$gt':?0}}")
    Flux<ContractTemplate> findProductIncAll(final Date date);
}