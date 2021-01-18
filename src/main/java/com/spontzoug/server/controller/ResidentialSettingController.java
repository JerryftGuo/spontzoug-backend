package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.ResidentialSetting;
import com.spontzoug.server.model.ResidentialSetting;
import com.spontzoug.server.service.IResidentialSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/residentialsetting")
public class ResidentialSettingController {
    @Autowired
    private IResidentialSettingService residentialSettingService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ResidentialSetting l) {  residentialSettingService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResidentialSetting> update(@RequestBody ResidentialSetting m) {
        return residentialSettingService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return residentialSettingService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<ResidentialSetting>> findById(@PathVariable("id") String id) {
        Mono<ResidentialSetting> m = residentialSettingService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<ResidentialSetting>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<ResidentialSetting> findByBusinessid(@PathVariable("id") String id){
        return residentialSettingService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<ResidentialSetting> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return residentialSettingService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<ResidentialSetting> findDynamics(@PathVariable("id") String id){
        return residentialSettingService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<ResidentialSetting> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return residentialSettingService.findDynamicsInc(id,date);
    }

    @GetMapping("/all")
    public Flux<ResidentialSetting> findAll(){
        return residentialSettingService.findAll();
    }

    @GetMapping("/all/{date}")
    public Flux<ResidentialSetting> findAllInc(
            @PathVariable("date") Date date  ){
        return residentialSettingService.findAllInc(date);
    }

    @GetMapping("/region/{region}")
    public Flux<ResidentialSetting> findByRegion(
            @PathVariable("region") String region
    ){
        return residentialSettingService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<ResidentialSetting> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return residentialSettingService.findByRegionInc(region,date);
    }
}
