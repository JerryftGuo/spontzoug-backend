package com.spontzoug.server.repository;

import com.spontzoug.server.model.TaxRate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface ITaxRateRepository extends ReactiveMongoRepository<TaxRate, String> {
    @Query("{'businessid':?0}")
    Flux<TaxRate> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<TaxRate> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<TaxRate> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<TaxRate> findDynamicsInc(final String id, final Date date);
}