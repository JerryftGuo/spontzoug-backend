package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.HealthSetting;
import com.spontzoug.server.model.HealthSetting;
import com.spontzoug.server.service.IHealthSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/healthsetting")
public class HealthSettingController {
    @Autowired
    private IHealthSettingService healthSettingService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody HealthSetting l) {  healthSettingService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<HealthSetting> update(@RequestBody HealthSetting m) {
        return healthSettingService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return healthSettingService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<HealthSetting>> findById(@PathVariable("id") String id) {
        Mono<HealthSetting> m = healthSettingService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<HealthSetting>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<HealthSetting> findByBusinessid(@PathVariable("id") String id){
        return healthSettingService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<HealthSetting> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return healthSettingService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<HealthSetting> findDynamics(@PathVariable("id") String id){
        return healthSettingService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<HealthSetting> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return healthSettingService.findDynamicsInc(id,date);
    }

    @GetMapping("/all")
    public Flux<HealthSetting> findAll(){
        return healthSettingService.findAll();
    }

    @GetMapping("/all/{date}")
    public Flux<HealthSetting> findAllInc(
            @PathVariable("date") Date date  ){
        return healthSettingService.findAllInc(date);
    }

    @GetMapping("/region/{region}")
    public Flux<HealthSetting> findByRegion(
            @PathVariable("region") String region
    ){
        return healthSettingService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<HealthSetting> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return healthSettingService.findByRegionInc(region,date);
    }
}
