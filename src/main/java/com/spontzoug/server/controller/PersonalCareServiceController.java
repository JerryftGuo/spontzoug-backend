package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.PersonalCareService;
import com.spontzoug.server.service.IPersonalCareServiceService;
import com.spontzoug.server.service.IPersonalCareServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/personalcareservice")
public class PersonalCareServiceController {
    @Autowired
    private IPersonalCareServiceService personalcareService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PersonalCareService l) {  personalcareService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PersonalCareService> update(@RequestBody PersonalCareService l) {
        return personalcareService.update(l);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return personalcareService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<PersonalCareService>> findById(@PathVariable("id") String id) {
        Mono<PersonalCareService> l = personalcareService.findById(id);
        HttpStatus s = l!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<PersonalCareService>>(l,s);
    }
    @GetMapping("/businessconfig/{id}")
    public Mono<Long> findByBusinessConf(@PathVariable("id") String id) {
        return personalcareService.countActive(id);
    }
    @GetMapping("/businessid/{id}")
    public Flux<PersonalCareService> findByBusinessid(@PathVariable("id") String id){
        return personalcareService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<PersonalCareService> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return personalcareService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<PersonalCareService> findDynamics(
            @PathVariable("id") String id){
        return personalcareService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<PersonalCareService> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return personalcareService.findDynamicsInc(id,date);
    }

    @GetMapping("/region/{region}")
    public Flux<PersonalCareService> findByRegion(@PathVariable("region") String region){
        return personalcareService.findByRegion(region);
    }
    @GetMapping("/region/{region}/{date}")
    public Flux<PersonalCareService> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return personalcareService.findByRegionInc(region,date);
    }
}
