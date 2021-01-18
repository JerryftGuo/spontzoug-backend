package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.RetailerCate;
import com.spontzoug.server.service.IRetailerCateService;
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
@RequestMapping("/api/retailercate")
public class RetailerCateController {
    @Autowired
    private IRetailerCateService retailerCateService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody RetailerCate l) {
        log.info("created menu cat called");
        retailerCateService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<RetailerCate> update(@RequestBody RetailerCate m) {
        return retailerCateService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return retailerCateService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<RetailerCate>> findById(@PathVariable("id") String id) {
        Mono<RetailerCate> m = retailerCateService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<RetailerCate>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<RetailerCate> findByBusinessid(@PathVariable("id") String id){
        log.info("get category called");
        return retailerCateService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<RetailerCate> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return retailerCateService.findByBusinessidInc(id,date);
    }

    @GetMapping("/dynamic/{id}")
    public Flux<RetailerCate> findDynamics(@PathVariable("id") String id){
        log.info("get category dynamic called");
        return retailerCateService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<RetailerCate> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return retailerCateService.findDynamicsInc(id,date);
    }

    @GetMapping("/region/{region}")
    public Flux<RetailerCate> findByRegion(
            @PathVariable("region") String region
    ){
        return retailerCateService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<RetailerCate> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return retailerCateService.findByRegionInc(region,date);
    }

}
