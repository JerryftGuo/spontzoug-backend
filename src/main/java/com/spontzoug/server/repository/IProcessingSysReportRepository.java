package com.spontzoug.server.repository;

import com.spontzoug.server.http.SysReportProcessingResponse;
import com.spontzoug.server.model.ProcessingSysReport;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IProcessingSysReportRepository extends ReactiveMongoRepository<ProcessingSysReport, String> {
    @Query(value="{'region': ?0, 'created':{'$gte':?1, '$lt': ?2}}")
    Flux<SysReportProcessingResponse> findDayReport(final String region, final Date date1, final Date date2);
    @Query(value="{'region': ?0, 'created':{'$gte':?1, '$lt': ?2}}")
    Flux<SysReportProcessingResponse> findMonthReport(final String region, final Date date1, final Date date2);

    @Query(value="{'region': ?0, 'created':{'$gte':?1, '$lt': ?2}, 'modified':{'$gte':?3} }")
    Flux<SysReportProcessingResponse> findDayReportInc(final String region, final Date date1, final Date date2, final Date timestamp);
    @Query(value="{'region': ?0, 'created':{'$gte':?1, '$lt': ?2}, 'modified':{'$gte':?3} }")
    Flux<SysReportProcessingResponse> findMonthReportInc(final String region, final Date date1, final Date date2, final Date timestamp);


}