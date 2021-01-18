package com.spontzoug.server.repository;

import com.spontzoug.server.model.*;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IFinancialInsuranceSettingRepository extends ReactiveMongoRepository<FinancialInsuranceSetting, String> {
    @Query("{'businessid':?0}")
    Flux<FinancialInsuranceSetting> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<FinancialInsuranceSetting> findByBusinessidInc(final String id, final Date date);
    @Query("{'businessid':?0}")
    Flux<FinancialInsuranceSetting> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<FinancialInsuranceSetting> findDynamicsInc(final String id, final Date date);
    @Query(value = "{}" )
    Flux<FinancialInsuranceSetting> findAll();
    @Query(value="{'modified':{'$gt':?0 }")
    Flux<FinancialInsuranceSetting> findAllInc(final Date date );
    @Query(value="{'region':?0 }")
    Flux<FinancialInsuranceSetting> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'$gt':?1 }")
    Flux<FinancialInsuranceSetting> findByRegionInc(final String region, final Date date );
}