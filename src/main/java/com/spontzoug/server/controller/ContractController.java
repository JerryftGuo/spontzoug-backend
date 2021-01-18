package com.spontzoug.server.controller;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.Contract;
import com.spontzoug.server.service.IContractService;
import com.spontzoug.server.service.IContractTemplateService;
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
@RequestMapping("/api/contract")
public class ContractController {
    @Autowired
    private IContractService contractService;
    @Autowired
    private IProcessingSysReportService processingSysReportService;
    @Autowired
    private IContractTemplateService contractTemplateService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Contract cont) {
         contractService.create(cont);

                /*.switchIfEmpty(
            contractTemplateService.findProductByName("all")
                .subscribe( ele -> {
                    cont.setData(ele.getData());
                    return contractService.create(cont);
            })
        );*/

    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Contract> update(@RequestBody Contract m) {
        return contractService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return contractService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Contract>> findById(@PathVariable("id") String id) {
        Mono<Contract> m = contractService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Contract>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<Contract> findByBusinessid(
            @PathVariable("id") String id ){
        return contractService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<Contract> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date ){
        return contractService.findByBusinessidInc(id,date);
    }


    @GetMapping("/report/day/{businessid}/{date1}/{date2}")
    public Flux<Contract> findDayReport(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return contractService.findDayReport(id,date1, date2);
    }

    @GetMapping("/report/month/{businessid}/{date1}/{date2}")
    public Flux<Contract> findMonthReport(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return contractService.findMonthReport(id,date1, date2);
    }


    @GetMapping("/report/day/{businessid}/{date1}/{date2}/{timestamp}")
    public Flux<Contract> findDayReportInc(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp){
        return contractService.findDayReport(id,date1, date2);
    }

    @GetMapping("/report/month/{businessid}/{date1}/{date2}/{timestamp}")
    public Flux<Contract> findMonthReportInc(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp){
        return contractService.findMonthReportInc(id,date1, date2,timestamp);
    }


    @GetMapping("/region/{region}")
    public Flux<Contract> findByRegion(
            @PathVariable("region") String region
    ){
        return contractService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<Contract> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return contractService.findByRegionInc(region,date);
    }
}
