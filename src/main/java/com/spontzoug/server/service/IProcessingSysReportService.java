package com.spontzoug.server.service;


import com.spontzoug.server.http.SysReportProcessingResponse;
import com.spontzoug.server.model.ProcessingSysReport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IProcessingSysReportService {
    void create(ProcessingSysReport b);
    Mono<ProcessingSysReport> findById(final String id);
    Mono<ProcessingSysReport> update(ProcessingSysReport service);
    Mono<Void> deleteById(final String id);
    Flux<SysReportProcessingResponse> findDayReport(
            final String region, final Date date1, final Date date2);
    Flux<SysReportProcessingResponse> findMonthReport(
            final String region, final Date date1, final Date date2);
    Flux<SysReportProcessingResponse> findDayReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp);
    Flux<SysReportProcessingResponse> findMonthReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp);

}