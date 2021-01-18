package com.spontzoug.server.repository;

import com.spontzoug.server.model.ContractSignature;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface IContractSignatureRepository extends ReactiveMongoRepository<ContractSignature, String> {
    @Query("{'name':?0}")
    Mono<ContractSignature> findByName(final String name);
}