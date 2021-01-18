package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.FoodCate;
import com.spontzoug.server.service.IFoodCateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/foodcate")
public class FoodCateController {
    @Autowired
    private IFoodCateService foodCateService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody FoodCate l) {
        log.info("created menu cat called");
        foodCateService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<FoodCate> update(@RequestBody FoodCate m) {
        return foodCateService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return foodCateService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<FoodCate>> findById(@PathVariable("id") String id) {
        Mono<FoodCate> m = foodCateService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<FoodCate>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<FoodCate> findByBusinessid(@PathVariable("id") String id){
        log.info("get category called");
        return foodCateService.findByBusinessid(id);
    }

    @GetMapping("/businessid/{id}/{date}")
    public Flux<FoodCate> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return foodCateService.findByBusinessidInc(id,date);
    }

    @GetMapping("/dynamic/{id}")
    public Flux<FoodCate> findDynamics(@PathVariable("id") String id){
        log.info("get category dynamic called");
        return foodCateService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<FoodCate> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return foodCateService.findDynamicsInc(id,date);
    }

    @GetMapping("/region/{region}")
    public Flux<FoodCate> findByRegion(
            @PathVariable("region") String region
    ){
        return foodCateService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<FoodCate> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return foodCateService.findByRegionInc(region,date);
    }

}
