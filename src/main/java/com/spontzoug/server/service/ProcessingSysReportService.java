package com.spontzoug.server.service;

import com.spontzoug.server.http.SysReportProcessingResponse;
import com.spontzoug.server.model.ProcessingSysReport;
import com.spontzoug.server.repository.IProcessingSysReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class ProcessingSysReportService implements IProcessingSysReportService {
    @Autowired
    private IProcessingSysReportRepository processingRepository;

    public void create(ProcessingSysReport processing) {
        processingRepository.save(processing).subscribe();
    }

    public Mono<ProcessingSysReport> update(ProcessingSysReport processing){
        return processingRepository.save(processing);
    }

    public Mono<Void> deleteById(final String id){
        return processingRepository.deleteById(id);
    }

    public Mono<ProcessingSysReport> findById(final String id){
        return processingRepository.findById(id);
    }

    public Flux<SysReportProcessingResponse> findDayReport(
            final String region, final Date date1, final Date date2){
        return processingRepository.findDayReport(region,date1, date2);
    }
    public Flux<SysReportProcessingResponse> findMonthReport(
            final String region, final Date date1, final Date date2){
        return processingRepository.findMonthReport(region,date1, date2);
    }


    public Flux<SysReportProcessingResponse> findDayReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp){
        return processingRepository.findDayReportInc(region,date1, date2, timestamp);
    }
    public Flux<SysReportProcessingResponse> findMonthReportInc(
            final String region, final Date date1, final Date date2, final Date timestamp){
        return processingRepository.findMonthReportInc(region,date1, date2, timestamp);
    }
}