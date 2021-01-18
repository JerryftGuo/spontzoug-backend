package com.spontzoug.server.controller;

import com.spontzoug.server.model.AutomotiveServiceService;
import com.spontzoug.server.service.IAutomotiveServiceServiceService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/automotiveserviceservice")
public class AutomotiveServiceServiceController {
    @Autowired
    private IAutomotiveServiceServiceService automotiveserviceService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AutomotiveServiceService l) {  automotiveserviceService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<AutomotiveServiceService> update(@RequestBody AutomotiveServiceService l) {
        return automotiveserviceService.update(l);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return automotiveserviceService.deleteById(id);
    }

    @GetMapping("/businessconfig/{id}")
    public Mono<Long> findByBusinessConf(@PathVariable("id") String id) {
        return automotiveserviceService.countActive(id);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<AutomotiveServiceService>> findById(@PathVariable("id") String id) {
        Mono<AutomotiveServiceService> l = automotiveserviceService.findById(id);
        HttpStatus s = l!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<AutomotiveServiceService>>(l,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<AutomotiveServiceService> findByBusinessid(@PathVariable("id") String id){
        return automotiveserviceService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<AutomotiveServiceService> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return automotiveserviceService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<AutomotiveServiceService> findDynamics(
            @PathVariable("id") String id){
        return automotiveserviceService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<AutomotiveServiceService> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return automotiveserviceService.findDynamicsInc(id,date);
    }

    @GetMapping("/region/{region}")
    public Flux<AutomotiveServiceService> findByRegion(@PathVariable("region") String region){
        return automotiveserviceService.findByRegion(region);
    }
    @GetMapping("/region/{region}/{date}")
    public Flux<AutomotiveServiceService> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return automotiveserviceService.findByRegionInc(region,date);
    }
}
