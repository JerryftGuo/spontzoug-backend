package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.FoodMenu;
import com.spontzoug.server.service.IFoodMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/foodmenu")
public class FoodMenuController {
    @Autowired
    private IFoodMenuService foodMenuService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody FoodMenu l) {  foodMenuService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<FoodMenu> update(@RequestBody FoodMenu m) {
        return foodMenuService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return foodMenuService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<FoodMenu>> findById(@PathVariable("id") String id) {
        Mono<FoodMenu> m = foodMenuService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<FoodMenu>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<FoodMenu> findByBusinessid(@PathVariable("id") String id){
        return foodMenuService.findByBusinessid(id);
    }
    @GetMapping("/businessconfig/{id}")
    public Mono<Long> findByBusinessConf(@PathVariable("id") String id){
        return foodMenuService.countActive(id);
    }

    @GetMapping("/businessid/{id}/{date}")
    public Flux<FoodMenu> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return foodMenuService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<FoodMenu> findDynamics(
            @PathVariable("id") String id){
        return foodMenuService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<FoodMenu> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return foodMenuService.findDynamicsInc(id,date);
    }


    @GetMapping("/region/{region}")
    public Flux<FoodMenu> findByRegion(
            @PathVariable("region") String region
    ){
        return foodMenuService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<FoodMenu> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return foodMenuService.findByRegionInc(region,date);
    }

}
