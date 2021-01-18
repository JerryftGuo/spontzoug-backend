package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.ResidentialCate;
import com.spontzoug.server.service.IResidentialCateService;
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
@RequestMapping("/api/residentialcate")
public class ResidentialCateController {
    @Autowired
    private IResidentialCateService residentialCateService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ResidentialCate l) {
        log.info("created menu cat called");
        residentialCateService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResidentialCate> update(@RequestBody ResidentialCate m) {
        return residentialCateService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return residentialCateService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<ResidentialCate>> findById(@PathVariable("id") String id) {
        Mono<ResidentialCate> m = residentialCateService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<ResidentialCate>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<ResidentialCate> findByBusinessid(@PathVariable("id") String id){
        log.info("get category called");
        return residentialCateService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<ResidentialCate> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return residentialCateService.findByBusinessidInc(id,date);
    }

    @GetMapping("/dynamic/{id}")
    public Flux<ResidentialCate> findDynamics(@PathVariable("id") String id){
        log.info("get category dynamic called");
        return residentialCateService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<ResidentialCate> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return residentialCateService.findDynamicsInc(id,date);
    }

    @GetMapping("/region/{region}")
    public Flux<ResidentialCate> findByRegion(
            @PathVariable("region") String region
    ){
        return residentialCateService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<ResidentialCate> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return residentialCateService.findByRegionInc(region,date);
    }

}
