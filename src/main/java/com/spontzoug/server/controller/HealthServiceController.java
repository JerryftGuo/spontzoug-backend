package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.HealthService;
import com.spontzoug.server.service.IHealthServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/healthservice")
public class HealthServiceController {
    @Autowired
    private IHealthServiceService healthService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody HealthService l) {  healthService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<HealthService> update(@RequestBody HealthService l) {
        return healthService.update(l);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return healthService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<HealthService>> findById(@PathVariable("id") String id) {
        Mono<HealthService> l = healthService.findById(id);
        HttpStatus s = l!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<HealthService>>(l,s);
    }

    @GetMapping("/businessconfig/{id}")
    public Mono<Long> findByBusinessConf(@PathVariable("id") String id) {
        return healthService.countActive(id);
    }

    @GetMapping("/businessid/{id}")
    public Flux<HealthService> findByBusinessid(@PathVariable("id") String id){
        return healthService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<HealthService> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return healthService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<HealthService> findDynamics(
            @PathVariable("id") String id){
        return healthService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<HealthService> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return healthService.findDynamicsInc(id,date);
    }

    @GetMapping("/region/{region}")
    public Flux<HealthService> findByRegion(@PathVariable("region") String region){
        return healthService.findByRegion(region);
    }
    @GetMapping("/region/{region}/{date}")
    public Flux<HealthService> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return healthService.findByRegionInc(region,date);
    }
}
