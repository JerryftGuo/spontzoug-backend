package com.spontzoug.server.repository;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.Contract;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IContractRepository extends ReactiveMongoRepository<Contract,String> {
    @Query(value="{'region':?0 }")
    Flux<Contract> findByRegion(final String region );
    @Query(value="{'region':?0,'modified':{'gt':?1 }}")
    Flux<Contract> findByRegionInc(final String region, final Date date );
    @Query(value="{'clientid': ?0}" )
    Flux<Contract> findByBusinessid(final String id );
    @Query(value="{'clientid':?0,'modified':{'$gt':?1}}")
    Flux<Contract> findByBusinessidInc(final String id, final Date date );

    @Query(value="{'businessid': ?0, 'start':{'$gt':?1, '$lt': ?2}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<Contract> findDayReport(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2}}",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'end':0,'created':0, 'modified':0, 'creator':0}")
    Flux<Contract> findMonthReport(final String id, final Date date1, final Date date2);
    @Query(value="{'businessid':?0, 'start':{'$gte': ?1, '$lt': ?2}}", count = true )
    Mono<Long> countContract(final String id, final Date date1, final Date date2);

    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2},'modified':{'$gt':?3} }",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'created':0, 'modified':0, 'creator':0}")
    Flux<Contract> findDayReportInc(final String id, final Date date1, final Date date2, final Date timestamp);
    @Query(value="{'businessid': ?0, 'start':{'$gte':?1, '$lt': ?2},'modified':{'$gt':?3} }",
            fields="{'client':0, 'familyid':0, 'room':0, 'note':0, 'end':0,'created':0, 'modified':0, 'creator':0}")
    Flux<Contract> findMonthReportInc(final String id, final Date date1, final Date date2, final Date timestamp);

}