package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.AutomativeSaleSetting;
import com.spontzoug.server.model.AutomativeSaleSetting;
import com.spontzoug.server.service.IAutomativeSaleSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/automativesalesetting")
public class AutomativeSaleSettingController {
    @Autowired
    private IAutomativeSaleSettingService personalcareSettingService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AutomativeSaleSetting l) {  personalcareSettingService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<AutomativeSaleSetting> update(@RequestBody AutomativeSaleSetting m) {
        return personalcareSettingService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return personalcareSettingService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<AutomativeSaleSetting>> findById(@PathVariable("id") String id) {
        Mono<AutomativeSaleSetting> m = personalcareSettingService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<AutomativeSaleSetting>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<AutomativeSaleSetting> findByBusinessid(@PathVariable("id") String id){
        return personalcareSettingService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<AutomativeSaleSetting> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return personalcareSettingService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<AutomativeSaleSetting> findDynamics(@PathVariable("id") String id){
        return personalcareSettingService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<AutomativeSaleSetting> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return personalcareSettingService.findDynamicsInc(id,date);
    }

    @GetMapping("/all")
    public Flux<AutomativeSaleSetting> findAll(){
        return personalcareSettingService.findAll();
    }

    @GetMapping("/all/{date}")
    public Flux<AutomativeSaleSetting> findAllInc(
            @PathVariable("date") Date date  ){
        return personalcareSettingService.findAllInc(date);
    }

    @GetMapping("/region/{region}")
    public Flux<AutomativeSaleSetting> findByRegion(
            @PathVariable("region") String region
    ){
        return personalcareSettingService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<AutomativeSaleSetting> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return personalcareSettingService.findByRegionInc(region,date);
    }
}
