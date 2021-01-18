package com.spontzoug.server.controller;

import com.spontzoug.server.model.AutomotiveSaleSetting;
import com.spontzoug.server.service.IAutomotiveSaleSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/automotivesalesetting")
public class AutomotiveSaleSettingController {
    @Autowired
    private IAutomotiveSaleSettingService personalcareSettingService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AutomotiveSaleSetting l) {  personalcareSettingService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<AutomotiveSaleSetting> update(@RequestBody AutomotiveSaleSetting m) {
        return personalcareSettingService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return personalcareSettingService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<AutomotiveSaleSetting>> findById(@PathVariable("id") String id) {
        Mono<AutomotiveSaleSetting> m = personalcareSettingService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<AutomotiveSaleSetting>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<AutomotiveSaleSetting> findByBusinessid(@PathVariable("id") String id){
        return personalcareSettingService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<AutomotiveSaleSetting> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return personalcareSettingService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<AutomotiveSaleSetting> findDynamics(@PathVariable("id") String id){
        return personalcareSettingService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<AutomotiveSaleSetting> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return personalcareSettingService.findDynamicsInc(id,date);
    }

    @GetMapping("/all")
    public Flux<AutomotiveSaleSetting> findAll(){
        return personalcareSettingService.findAll();
    }

    @GetMapping("/all/{date}")
    public Flux<AutomotiveSaleSetting> findAllInc(
            @PathVariable("date") Date date  ){
        return personalcareSettingService.findAllInc(date);
    }

    @GetMapping("/region/{region}")
    public Flux<AutomotiveSaleSetting> findByRegion(
            @PathVariable("region") String region
    ){
        return personalcareSettingService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<AutomotiveSaleSetting> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return personalcareSettingService.findByRegionInc(region,date);
    }
}
