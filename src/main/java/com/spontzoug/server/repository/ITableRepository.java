package com.spontzoug.server.repository;

import com.spontzoug.server.model.Promotion;
import com.spontzoug.server.model.Table;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface ITableRepository extends ReactiveMongoRepository<Table, String> {
    @Query(value="{'businessid':?0}")
    Flux<Table> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Table> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0}")
    Flux<Table> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Table> findDynamicsInc(final String id, final Date date);
}