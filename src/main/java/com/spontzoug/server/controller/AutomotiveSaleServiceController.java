package com.spontzoug.server.controller;


import com.spontzoug.server.model.AutomotiveSaleService;
import com.spontzoug.server.service.IAutomotiveSaleServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/automotivesaleservice")
public class AutomotiveSaleServiceController {
    @Autowired
    private IAutomotiveSaleServiceService automotivesaleService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AutomotiveSaleService l) {  automotivesaleService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<AutomotiveSaleService> update(@RequestBody AutomotiveSaleService l) {
        return automotivesaleService.update(l);
    }

    @GetMapping("/businessconfig/{id}")
    public Mono<Long> findByBusinessConf(@PathVariable("id") String id) {
        return automotivesaleService.countActive(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return automotivesaleService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<AutomotiveSaleService>> findById(@PathVariable("id") String id) {
        Mono<AutomotiveSaleService> l = automotivesaleService.findById(id);
        HttpStatus s = l!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<AutomotiveSaleService>>(l,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<AutomotiveSaleService> findByBusinessid(@PathVariable("id") String id){
        return automotivesaleService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<AutomotiveSaleService> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return automotivesaleService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<AutomotiveSaleService> findDynamics(
            @PathVariable("id") String id){
        return automotivesaleService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<AutomotiveSaleService> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return automotivesaleService.findDynamicsInc(id,date);
    }

    @GetMapping("/region/{region}")
    public Flux<AutomotiveSaleService> findByRegion(@PathVariable("region") String region){
        return automotivesaleService.findByRegion(region);
    }
    @GetMapping("/region/{region}/{date}")
    public Flux<AutomotiveSaleService> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return automotivesaleService.findByRegionInc(region,date);
    }
}
