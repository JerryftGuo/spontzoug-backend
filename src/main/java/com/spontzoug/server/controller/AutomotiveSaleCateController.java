package com.spontzoug.server.controller;

import com.spontzoug.server.model.AutomotiveSaleCate;
import com.spontzoug.server.service.IAutomotiveSaleCateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/automotivesalecate")
public class AutomotiveSaleCateController {
    @Autowired
    private IAutomotiveSaleCateService automotiveSaleCateService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AutomotiveSaleCate l) {
        log.info("created menu cat called");
        automotiveSaleCateService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<AutomotiveSaleCate> update(@RequestBody AutomotiveSaleCate m) {
        return automotiveSaleCateService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return automotiveSaleCateService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<AutomotiveSaleCate>> findById(@PathVariable("id") String id) {
        Mono<AutomotiveSaleCate> m = automotiveSaleCateService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<AutomotiveSaleCate>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<AutomotiveSaleCate> findByBusinessid(@PathVariable("id") String id){
        log.info("get category called");
        return automotiveSaleCateService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<AutomotiveSaleCate> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return automotiveSaleCateService.findByBusinessidInc(id,date);
    }

    @GetMapping("/dynamic/{id}")
    public Flux<AutomotiveSaleCate> findDynamics(@PathVariable("id") String id){
        log.info("get category dynamic called");
        return automotiveSaleCateService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<AutomotiveSaleCate> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return automotiveSaleCateService.findDynamicsInc(id,date);
    }

    @GetMapping("/region/{region}")
    public Flux<AutomotiveSaleCate> findByRegion(
            @PathVariable("region") String region
    ){
        return automotiveSaleCateService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<AutomotiveSaleCate> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return automotiveSaleCateService.findByRegionInc(region,date);
    }

}
