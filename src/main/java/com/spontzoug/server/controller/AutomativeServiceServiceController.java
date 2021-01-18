package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.AutomativeServiceService;
import com.spontzoug.server.service.IAutomativeServiceServiceService;
import com.spontzoug.server.service.IAutomativeServiceServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/automativeserviceservice")
public class AutomativeServiceServiceController {
    @Autowired
    private IAutomativeServiceServiceService automativeserviceService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AutomativeServiceService l) {  automativeserviceService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<AutomativeServiceService> update(@RequestBody AutomativeServiceService l) {
        return automativeserviceService.update(l);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return automativeserviceService.deleteById(id);
    }

    @GetMapping("/businessconfig/{id}")
    public Mono<Long> findByBusinessConf(@PathVariable("id") String id) {
        return automativeserviceService.countActive(id);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<AutomativeServiceService>> findById(@PathVariable("id") String id) {
        Mono<AutomativeServiceService> l = automativeserviceService.findById(id);
        HttpStatus s = l!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<AutomativeServiceService>>(l,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<AutomativeServiceService> findByBusinessid(@PathVariable("id") String id){
        return automativeserviceService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<AutomativeServiceService> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return automativeserviceService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<AutomativeServiceService> findDynamics(
            @PathVariable("id") String id){
        return automativeserviceService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<AutomativeServiceService> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return automativeserviceService.findDynamicsInc(id,date);
    }

    @GetMapping("/region/{region}")
    public Flux<AutomativeServiceService> findByRegion(@PathVariable("region") String region){
        return automativeserviceService.findByRegion(region);
    }
    @GetMapping("/region/{region}/{date}")
    public Flux<AutomativeServiceService> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return automativeserviceService.findByRegionInc(region,date);
    }
}
