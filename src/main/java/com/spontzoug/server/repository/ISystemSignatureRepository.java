package com.spontzoug.server.repository;

import com.spontzoug.server.model.SystemSignature;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISystemSignatureRepository extends ReactiveMongoRepository<SystemSignature, String> {
    @Query("{'name':?0}")
    Mono<SystemSignature> findByName(final String name);
    @Query(value="{'basename':?0}", fields = "{'signature':0}")
    Flux<SystemSignature> findByBaseName(final String name);
}