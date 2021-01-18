package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.RealEstateSetting;
import com.spontzoug.server.model.RealEstateSetting;
import com.spontzoug.server.service.IRealEstateSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/realestatesetting")
public class RealEstateSettingController {
    @Autowired
    private IRealEstateSettingService realestateSettingService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody RealEstateSetting l) {  realestateSettingService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<RealEstateSetting> update(@RequestBody RealEstateSetting m) {
        return realestateSettingService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return realestateSettingService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<RealEstateSetting>> findById(@PathVariable("id") String id) {
        Mono<RealEstateSetting> m = realestateSettingService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<RealEstateSetting>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<RealEstateSetting> findByBusinessid(@PathVariable("id") String id){
        return realestateSettingService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<RealEstateSetting> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return realestateSettingService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<RealEstateSetting> findDynamics(@PathVariable("id") String id){
        return realestateSettingService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<RealEstateSetting> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return realestateSettingService.findDynamicsInc(id,date);
    }
    @GetMapping("/all")
    public Flux<RealEstateSetting> findAll(){
        return realestateSettingService.findAll();
    }

    @GetMapping("/all/{date}")
    public Flux<RealEstateSetting> findAllInc(
            @PathVariable("date") Date date  ){
        return realestateSettingService.findAllInc(date);
    }

    @GetMapping("/region/{region}")
    public Flux<RealEstateSetting> findByRegion(
            @PathVariable("region") String region
    ){
        return realestateSettingService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<RealEstateSetting> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return realestateSettingService.findByRegionInc(region,date);
    }

}
