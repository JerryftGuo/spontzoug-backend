package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.RetailerSetting;
import com.spontzoug.server.model.RetailerSetting;
import com.spontzoug.server.service.IRetailerSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/retailersetting")
public class RetailerSettingController {
    @Autowired
    private IRetailerSettingService retailerSettingService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody RetailerSetting l) {  retailerSettingService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<RetailerSetting> update(@RequestBody RetailerSetting m) {
        return retailerSettingService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return retailerSettingService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<RetailerSetting>> findById(@PathVariable("id") String id) {
        Mono<RetailerSetting> m = retailerSettingService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<RetailerSetting>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<RetailerSetting> findByBusinessid(@PathVariable("id") String id){
        return retailerSettingService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<RetailerSetting> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return retailerSettingService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<RetailerSetting> findDynamics(@PathVariable("id") String id){
        return retailerSettingService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<RetailerSetting> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return retailerSettingService.findDynamicsInc(id,date);
    }
    @GetMapping("/all")
    public Flux<RetailerSetting> findAll(){
        return retailerSettingService.findAll();
    }

    @GetMapping("/all/{date}")
    public Flux<RetailerSetting> findAllInc(
            @PathVariable("date") Date date  ){
        return retailerSettingService.findAllInc(date);
    }

    @GetMapping("/region/{region}")
    public Flux<RetailerSetting> findByRegion(
            @PathVariable("region") String region
    ){
        return retailerSettingService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<RetailerSetting> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return retailerSettingService.findByRegionInc(region,date);
    }

}
