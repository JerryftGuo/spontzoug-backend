package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.FinancialInsuranceSetting;
import com.spontzoug.server.model.FinancialInsuranceSetting;
import com.spontzoug.server.service.IFinancialInsuranceSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/financialinsurancesetting")
public class FinancialInsuranceSettingController {
    @Autowired
    private IFinancialInsuranceSettingService financialinsuranceSettingService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody FinancialInsuranceSetting l) {  financialinsuranceSettingService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<FinancialInsuranceSetting> update(@RequestBody FinancialInsuranceSetting m) {
        return financialinsuranceSettingService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return financialinsuranceSettingService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<FinancialInsuranceSetting>> findById(@PathVariable("id") String id) {
        Mono<FinancialInsuranceSetting> m = financialinsuranceSettingService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<FinancialInsuranceSetting>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<FinancialInsuranceSetting> findByBusinessid(@PathVariable("id") String id){
        return financialinsuranceSettingService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<FinancialInsuranceSetting> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return financialinsuranceSettingService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<FinancialInsuranceSetting> findDynamics(@PathVariable("id") String id){
        return financialinsuranceSettingService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<FinancialInsuranceSetting> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return financialinsuranceSettingService.findDynamicsInc(id,date);
    }

    @GetMapping("/all")
    public Flux<FinancialInsuranceSetting> findAll(){
        return financialinsuranceSettingService.findAll();
    }

    @GetMapping("/all/{date}")
    public Flux<FinancialInsuranceSetting> findAllInc(
            @PathVariable("date") Date date  ){
        return financialinsuranceSettingService.findAllInc(date);
    }

    @GetMapping("/region/{region}")
    public Flux<FinancialInsuranceSetting> findByRegion(
            @PathVariable("region") String region
    ){
        return financialinsuranceSettingService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<FinancialInsuranceSetting> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return financialinsuranceSettingService.findByRegionInc(region,date);
    }
}
