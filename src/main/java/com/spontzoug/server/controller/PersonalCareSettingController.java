package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.PersonalCareSetting;
import com.spontzoug.server.model.PersonalCareSetting;
import com.spontzoug.server.service.IPersonalCareSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/personalcaresetting")
public class PersonalCareSettingController {
    @Autowired
    private IPersonalCareSettingService personalcareSettingService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PersonalCareSetting l) {  personalcareSettingService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PersonalCareSetting> update(@RequestBody PersonalCareSetting m) {
        return personalcareSettingService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return personalcareSettingService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<PersonalCareSetting>> findById(@PathVariable("id") String id) {
        Mono<PersonalCareSetting> m = personalcareSettingService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<PersonalCareSetting>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<PersonalCareSetting> findByBusinessid(@PathVariable("id") String id){
        return personalcareSettingService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<PersonalCareSetting> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return personalcareSettingService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<PersonalCareSetting> findDynamics(@PathVariable("id") String id){
        return personalcareSettingService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<PersonalCareSetting> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return personalcareSettingService.findDynamicsInc(id,date);
    }

    @GetMapping("/all")
    public Flux<PersonalCareSetting> findAll(){
        return personalcareSettingService.findAll();
    }

    @GetMapping("/all/{date}")
    public Flux<PersonalCareSetting> findAllInc(
            @PathVariable("date") Date date  ){
        return personalcareSettingService.findAllInc(date);
    }

    @GetMapping("/region/{region}")
    public Flux<PersonalCareSetting> findByRegion(
            @PathVariable("region") String region
    ){
        return personalcareSettingService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<PersonalCareSetting> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return personalcareSettingService.findByRegionInc(region,date);
    }
}
