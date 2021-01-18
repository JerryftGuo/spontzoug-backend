package com.spontzoug.server.repository;

import com.spontzoug.server.model.BusinessAccount;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IBusinessAccountRepository extends ReactiveMongoRepository<BusinessAccount, String> {
    @Query(value="{'region':?0 }")
    Flux<BusinessAccount> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'gt':?1 }}")
    Flux<BusinessAccount> findByRegionInc(final String region, final Date date );
    @Query(value = "{}" )
    Flux<BusinessAccount> findAll();
    @Query(value="{'modified':{'gt':?0 }")
    Flux<BusinessAccount> findAllInc(final Date date );

    @Query(value="{'creator':?0}")
    Flux<BusinessAccount> findByCreator(final String creator);

    @Query(value="{'id':?0}")
    Flux<BusinessAccount> findDynamics(final String id);
    @Query(value="{'id':?0,'modified':{'$gt':?1}}")
    Flux<BusinessAccount> findDynamicsInc(final String id,final Date date);
    @Query("{'businessid':?0}")
    Flux<BusinessAccount> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<BusinessAccount> findByBusinessidInc(final String id,final Date date);

}