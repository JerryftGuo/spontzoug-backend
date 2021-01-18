package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.AutomotiveSaleMenu;
import com.spontzoug.server.service.IAutomotiveSaleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/automotivesalemenu")
public class AutomotiveSaleMenuController {
    @Autowired
    private IAutomotiveSaleMenuService automotivesaleMenuService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AutomotiveSaleMenu l) {  automotivesaleMenuService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<AutomotiveSaleMenu> update(@RequestBody AutomotiveSaleMenu m) {
        return automotivesaleMenuService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return automotivesaleMenuService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<AutomotiveSaleMenu>> findById(@PathVariable("id") String id) {
        Mono<AutomotiveSaleMenu> m = automotivesaleMenuService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<AutomotiveSaleMenu>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<AutomotiveSaleMenu> findByBusinessid(@PathVariable("id") String id){
        return automotivesaleMenuService.findByBusinessid(id);
    }

    @GetMapping("/businessconfig/{id}")
    public Mono<Long> findByBusinessConf(@PathVariable("id") String id){
        return automotivesaleMenuService.countActive(id);
    }

    @GetMapping("/businessid/{id}/{date}")
    public Flux<AutomotiveSaleMenu> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return automotivesaleMenuService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<AutomotiveSaleMenu> findDynamics(
            @PathVariable("id") String id){
        return automotivesaleMenuService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<AutomotiveSaleMenu> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return automotivesaleMenuService.findDynamicsInc(id,date);
    }


    @GetMapping("/region/{region}")
    public Flux<AutomotiveSaleMenu> findByRegion(
            @PathVariable("region") String region
    ){
        return automotivesaleMenuService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<AutomotiveSaleMenu> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return automotivesaleMenuService.findByRegionInc(region,date);
    }

}
