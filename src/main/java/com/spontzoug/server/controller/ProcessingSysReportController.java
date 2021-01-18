package com.spontzoug.server.controller;

import com.spontzoug.server.http.SysReportProcessingResponse;
import com.spontzoug.server.model.ProcessingSysReport;
import com.spontzoug.server.service.IProcessingSysReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/processingsysreport")
public class ProcessingSysReportController {
    @Autowired
    private IProcessingSysReportService processingService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProcessingSysReport l) {
        processingService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ProcessingSysReport> update(@RequestBody ProcessingSysReport m) {
        log.info("put processing");
        return processingService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById(@PathVariable("id") String id) {
        log.info("del processing");
        return processingService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<ProcessingSysReport>> findById(@PathVariable("id") String id) {
        Mono<ProcessingSysReport> m = processingService.findById(id);
        HttpStatus s = m != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<ProcessingSysReport>>(m, s);
    }


    @GetMapping("/report/day/{region}/{date1}/{date2}")
    public Flux<SysReportProcessingResponse> findDayReport(
            @PathVariable("region") String region,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return processingService.findDayReport(region,date1, date2);
    }

    @GetMapping("/report/month/{region}/{date1}/{date2}")
    public Flux<SysReportProcessingResponse> findMonthReport(
            @PathVariable("region") String region,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return processingService.findMonthReport(region,date1, date2);
    }

    @GetMapping("/report/day/{region}/{date1}/{date2}/{timestamp}")
    public Flux<SysReportProcessingResponse> findDayReportInc(
            @PathVariable("region") String region,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp){
        return processingService.findDayReportInc(region,date1, date2, timestamp);
    }

    @GetMapping("/report/month/{region}/{date1}/{date2}/{timestamp}")
    public Flux<SysReportProcessingResponse> findMonthReportInc(
            @PathVariable("region") String region,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp){
        return processingService.findMonthReportInc(region,date1, date2, timestamp);
    }

}
