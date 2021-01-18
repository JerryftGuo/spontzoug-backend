package com.spontzoug.server.repository;

import com.spontzoug.server.model.ContractPaper;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface IContractPaperRepository extends ReactiveMongoRepository<ContractPaper, String> {
    @Query("{'name':?0}")
    Mono<ContractPaper> findByName(final String name);
}