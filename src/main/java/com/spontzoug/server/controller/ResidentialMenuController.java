package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.ResidentialMenu;
import com.spontzoug.server.service.IResidentialMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/residentialmenu")
public class ResidentialMenuController {
    @Autowired
    private IResidentialMenuService residentialMenuService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ResidentialMenu l) {  residentialMenuService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResidentialMenu> update(@RequestBody ResidentialMenu m) {
        return residentialMenuService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return residentialMenuService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<ResidentialMenu>> findById(@PathVariable("id") String id) {
        Mono<ResidentialMenu> m = residentialMenuService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<ResidentialMenu>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<ResidentialMenu> findByBusinessid(@PathVariable("id") String id){
        return residentialMenuService.findByBusinessid(id);
    }

    @GetMapping("/businessconfig/{id}")
    public Mono<Long> findByBusinessConf(@PathVariable("id") String id){
        return residentialMenuService.countActive(id);
    }

    @GetMapping("/businessid/{id}/{date}")
    public Flux<ResidentialMenu> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return residentialMenuService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<ResidentialMenu> findDynamics(
            @PathVariable("id") String id){
        return residentialMenuService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<ResidentialMenu> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return residentialMenuService.findDynamicsInc(id,date);
    }


    @GetMapping("/region/{region}")
    public Flux<ResidentialMenu> findByRegion(
            @PathVariable("region") String region
    ){
        return residentialMenuService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<ResidentialMenu> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return residentialMenuService.findByRegionInc(region,date);
    }

}
