package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.Promotion;
import com.spontzoug.server.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/promotion")
public class PromotionController {
    @Autowired
    private IPromotionService promotionService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Promotion l) {  promotionService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Promotion> update(@RequestBody Promotion m) {
        return promotionService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return promotionService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Promotion>> findById(@PathVariable("id") String id) {
        Mono<Promotion> m = promotionService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Promotion>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<Promotion> findByBusinessid(@PathVariable("id") String id){
        return promotionService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<Promotion> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return promotionService.findByBusinessidInc(id,date);
    }

    @GetMapping("/dynamic/{id}")
    public Flux<Promotion> findDynamics(
            @PathVariable("id") String id){
        return promotionService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<Promotion> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return promotionService.findDynamicsInc(id,date);
    }
}
