package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.RetailerMenu;
import com.spontzoug.server.service.IRetailerMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/retailermenu")
public class RetailerMenuController {
    @Autowired
    private IRetailerMenuService retailerMenuService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody RetailerMenu l) {  retailerMenuService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<RetailerMenu> update(@RequestBody RetailerMenu m) {
        return retailerMenuService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return retailerMenuService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<RetailerMenu>> findById(@PathVariable("id") String id) {
        Mono<RetailerMenu> m = retailerMenuService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<RetailerMenu>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<RetailerMenu> findByBusinessid(@PathVariable("id") String id){
        return retailerMenuService.findByBusinessid(id);
    }

    @GetMapping("/businessconfig/{id}")
    public Mono<Long> findByBusinessConf(@PathVariable("id") String id){
        return retailerMenuService.countActive(id);
    }

    @GetMapping("/businessid/{id}/{date}")
    public Flux<RetailerMenu> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return retailerMenuService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<RetailerMenu> findDynamics(
            @PathVariable("id") String id){
        return retailerMenuService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<RetailerMenu> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return retailerMenuService.findDynamicsInc(id,date);
    }


    @GetMapping("/region/{region}")
    public Flux<RetailerMenu> findByRegion(
            @PathVariable("region") String region
    ){
        return retailerMenuService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<RetailerMenu> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return retailerMenuService.findByRegionInc(region,date);
    }

}
