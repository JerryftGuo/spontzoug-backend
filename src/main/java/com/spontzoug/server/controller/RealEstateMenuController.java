package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.RealEstateMenu;
import com.spontzoug.server.service.IRealEstateMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/realestatemenu")
public class RealEstateMenuController {
    @Autowired
    private IRealEstateMenuService realestateMenuService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody RealEstateMenu l) {  realestateMenuService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<RealEstateMenu> update(@RequestBody RealEstateMenu m) {
        return realestateMenuService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return realestateMenuService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<RealEstateMenu>> findById(@PathVariable("id") String id) {
        Mono<RealEstateMenu> m = realestateMenuService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<RealEstateMenu>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<RealEstateMenu> findByBusinessid(@PathVariable("id") String id){
        return realestateMenuService.findByBusinessid(id);
    }

    @GetMapping("/businessconfig/{id}")
    public Mono<Long> findByBusinessConf(@PathVariable("id") String id){
        return realestateMenuService.countActive(id);
    }

    @GetMapping("/businessid/{id}/{date}")
    public Flux<RealEstateMenu> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return realestateMenuService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<RealEstateMenu> findDynamics(
            @PathVariable("id") String id){
        return realestateMenuService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<RealEstateMenu> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return realestateMenuService.findDynamicsInc(id,date);
    }


    @GetMapping("/region/{region}")
    public Flux<RealEstateMenu> findByRegion(
            @PathVariable("region") String region
    ){
        return realestateMenuService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<RealEstateMenu> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return realestateMenuService.findByRegionInc(region,date);
    }

}
