package com.spontzoug.server.controller;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.BusinessAccount;
import com.spontzoug.server.service.IBusinessAccountService;
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
@RequestMapping("/api/businessaccount")
public class BusinessAccountController {
    @Autowired
    private IBusinessAccountService businessaccountService;
    @Autowired
    private IProcessingSysReportService processingSysReportService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody BusinessAccount appt) {
        businessaccountService.create(appt);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<BusinessAccount> update(@RequestBody BusinessAccount m) {
        return businessaccountService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return businessaccountService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<BusinessAccount>> findById(@PathVariable("id") String id) {
        Mono<BusinessAccount> m = businessaccountService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<BusinessAccount>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<BusinessAccount> findByBusinessid(
            @PathVariable("id") String id ){
        return businessaccountService.findByBusinessid(id);
    }

    @GetMapping("/businessid/{id}/{date}")
    public Flux<BusinessAccount> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date ){
        return businessaccountService.findByBusinessidInc(id,date);
    }

    @GetMapping("/region/{region}")
    public Flux<BusinessAccount> findByRegion(
            @PathVariable("region") String region
    ){
        return businessaccountService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<BusinessAccount> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return businessaccountService.findByRegionInc(region,date);
    }
}
