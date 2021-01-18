package com.spontzoug.server.repository;

import com.spontzoug.server.model.Billing;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IBillingRepository extends ReactiveMongoRepository<Billing, String> {
    @Query("{'businessid':?0}")
    Flux<Billing> findByBusinessid(final String id);
    @Query("{'businessid':?0, 'modified':{'gt':?1}}")
    Flux<Billing> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'region':?0,'billing':{'$gt':?1, '$lt': ?2}}")
    Flux<Billing> findByDate(final String region,final Date date1, final Date date2);
    @Query("{'serial':?0}")
    Mono<Billing> findBySerial(final String serial );

    @Query(value="{'region':?0, 'created':{'$gte':?1, '$lt': ?2}}", fields = "{'data':0,'authoritycode':0,'businessid':0,'issuerid':0}")
    Flux<Billing> findDayReport(final String region, final Date date1, final Date date2);
    @Query(value="{'region':?0, 'created':{'$gte':?1, '$lt': ?2}}",fields = "{'data':0,'authoritycode':0,'businessid':0,'issuerid':0}")
    Flux<Billing> findMonthReport(final String region, final Date date1, final Date date2);
    @Query(value="{'region':?0,'created':{'$gte':?1, '$lt': ?2}, 'modified':{'$gte':?3} }",fields = "{'data':0,'authoritycode':0,'businessid':0,'issuerid':0}")
    Flux<Billing> findDayReportInc(final String region, final Date date1, final Date date2, final Date timestamp);
    @Query(value="{'region':?0, 'created':{'$gte':?1, '$lt': ?2}, 'modified':{'$gte':?3} }",fields = "{'data':0,'authoritycode':0,'businessid':0,'issuerid':0}")
    Flux<Billing> findMonthReportInc(final String region, final Date date1, final Date date2, final Date timestamp);

/*
    @Query(value="{'region':?0, 'created':{'$gte':?1, '$lt': ?2}}", fields = "{'data':0,'authoritycode':0,'businessid':0,'issuerid':0}")
    Flux<SysReportBillingResponse> findDayReport(final String region, final Date date1, final Date date2);
    @Query(value="{'region':?0, 'created':{'$gte':?1, '$lt': ?2}}",fields = "{'data':0,'authoritycode':0,'businessid':0,'issuerid':0}")
    Flux<SysReportBillingResponse> findMonthReport(final String region, final Date date1, final Date date2);
    @Query(value="{'region':?0, 'created':{'$gte':?1, '$lt': ?2}, 'modified':{'$gte':?3} }",fields = "{'data':0,'authoritycode':0,'businessid':0,'issuerid':0}")
    Flux<SysReportBillingResponse> findDayReportInc(final String region, final Date date1, final Date date2, final Date timestamp);
    @Query(value="{'region':?0, 'created':{'$gte':?1, '$lt': ?2}, 'modified':{'$gte':?3} }",fields = "{'data':0,'authoritycode':0,'businessid':0,'issuerid':0}")
    Flux<SysReportBillingResponse> findMonthReportInc(final String region, final Date date1, final Date date2, final Date timestamp);

 */
}