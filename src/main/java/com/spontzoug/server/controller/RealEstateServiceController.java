package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.RealEstateService;
import com.spontzoug.server.service.IRealEstateServiceService;
import com.spontzoug.server.service.IRealEstateServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/realestateservice")
public class RealEstateServiceController {
    @Autowired
    private IRealEstateServiceService realestateService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody RealEstateService l) {  realestateService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<RealEstateService> update(@RequestBody RealEstateService l) {
        return realestateService.update(l);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return realestateService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<RealEstateService>> findById(@PathVariable("id") String id) {
        Mono<RealEstateService> l = realestateService.findById(id);
        HttpStatus s = l!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<RealEstateService>>(l,s);
    }
    @GetMapping("/businessconfig/{id}")
    public Mono<Long> findByBusinessConf(@PathVariable("id") String id) {
        return realestateService.countActive(id);
    }
    @GetMapping("/businessid/{id}")
    public Flux<RealEstateService> findByBusinessid(@PathVariable("id") String id){
        return realestateService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<RealEstateService> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return realestateService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<RealEstateService> findDynamics(
            @PathVariable("id") String id){
        return realestateService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<RealEstateService> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return realestateService.findDynamicsInc(id,date);
    }

    @GetMapping("/region/{region}")
    public Flux<RealEstateService> findByRegion(@PathVariable("region") String region){
        return realestateService.findByRegion(region);
    }
    @GetMapping("/region/{region}/{date}")
    public Flux<RealEstateService> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return realestateService.findByRegionInc(region,date);
    }
}
