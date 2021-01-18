package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.SysPromotion;
import com.spontzoug.server.service.ISysPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/syspromotion")
public class SysPromotionController {
    @Autowired
    private ISysPromotionService sysPromotionService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody SysPromotion l) {  sysPromotionService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<SysPromotion> update(@RequestBody SysPromotion m) {
        return sysPromotionService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return sysPromotionService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<SysPromotion>> findById(@PathVariable("id") String id) {
        Mono<SysPromotion> m = sysPromotionService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<SysPromotion>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<SysPromotion> findByBusinessid(@PathVariable("id") String id){
        return sysPromotionService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<SysPromotion> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return sysPromotionService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<SysPromotion> findDynamics(
            @PathVariable("id") String id){
        return sysPromotionService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<SysPromotion> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return sysPromotionService.findDynamicsInc(id,date);
    }
}
