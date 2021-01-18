package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.service.IFoodSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/foodsetting")
public class FoodSettingController {
    @Autowired
    private IFoodSettingService foodSettingService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody FoodSetting l) {  foodSettingService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<FoodSetting> update(@RequestBody FoodSetting m) {
        return foodSettingService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return foodSettingService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<FoodSetting>> findById(@PathVariable("id") String id) {
        Mono<FoodSetting> m = foodSettingService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<FoodSetting>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<FoodSetting> findByBusinessid(@PathVariable("id") String id){
        return foodSettingService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<FoodSetting> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return foodSettingService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<FoodSetting> findDynamics(@PathVariable("id") String id){
        return foodSettingService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<FoodSetting> findDynamicsInc(@PathVariable("id") String id,@PathVariable("date") Date date){
        return foodSettingService.findDynamicsInc(id,date);
    }
    @GetMapping("/all")
    public Flux<FoodSetting> findAll(){
        return foodSettingService.findAll();
    }

    @GetMapping("/all/{date}")
    public Flux<FoodSetting> findAllInc(
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return foodSettingService.findAllInc(date);
    }

    @GetMapping("/region/{region}")
    public Flux<FoodSetting> findByRegion(
            @PathVariable("region") String region
    ){
        return foodSettingService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<FoodSetting> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return foodSettingService.findByRegionInc(region,date);
    }
    
}
