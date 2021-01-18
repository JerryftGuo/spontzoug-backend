package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.TaxRate;
import com.spontzoug.server.service.ITaxRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/taxrate")
public class TaxRateController {
    @Autowired
    private ITaxRateService taxRateService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody TaxRate l) {  taxRateService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<TaxRate> update(@RequestBody TaxRate m) {
        return taxRateService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return taxRateService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<TaxRate>> findById(@PathVariable("id") String id) {
        Mono<TaxRate> m = taxRateService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<TaxRate>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<TaxRate> findByBusinessid(
            @PathVariable("id") String id){
        return taxRateService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<TaxRate> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return taxRateService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<TaxRate> findDynamics(
            @PathVariable("id") String id){
        return taxRateService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<TaxRate> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return taxRateService.findDynamicsInc(id,date);
    }
}
