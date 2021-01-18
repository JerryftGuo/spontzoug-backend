package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.FinancialInsuranceService;
import com.spontzoug.server.service.IFinancialInsuranceServiceService;
import com.spontzoug.server.service.IFinancialInsuranceServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/financialinsuranceservice")
public class FinancialInsuranceServiceController {
    @Autowired
    private IFinancialInsuranceServiceService financialInsuranceService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody FinancialInsuranceService l) {  financialInsuranceService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<FinancialInsuranceService> update(@RequestBody FinancialInsuranceService l) {
        return financialInsuranceService.update(l);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return financialInsuranceService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<FinancialInsuranceService>> findById(@PathVariable("id") String id) {
        Mono<FinancialInsuranceService> l = financialInsuranceService.findById(id);
        HttpStatus s = l!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<FinancialInsuranceService>>(l,s);
    }
    @GetMapping("/businessconfig/{id}")
    public Mono<Long> findByBusinessConf(@PathVariable("id") String id) {
        return financialInsuranceService.countActive(id);
    }

    @GetMapping("/businessid/{id}")
    public Flux<FinancialInsuranceService> findByBusinessid(@PathVariable("id") String id){
        return financialInsuranceService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<FinancialInsuranceService> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return financialInsuranceService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<FinancialInsuranceService> findDynamics(
            @PathVariable("id") String id){
        return financialInsuranceService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<FinancialInsuranceService> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return financialInsuranceService.findDynamicsInc(id,date);
    }

    @GetMapping("/region/{region}")
    public Flux<FinancialInsuranceService> findByRegion(@PathVariable("region") String region){
        return financialInsuranceService.findByRegion(region);
    }
    @GetMapping("/region/{region}/{date}")
    public Flux<FinancialInsuranceService> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return financialInsuranceService.findByRegionInc(region,date);
    }
}
