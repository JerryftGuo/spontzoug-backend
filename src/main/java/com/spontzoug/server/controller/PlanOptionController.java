package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.PlanOption;
import com.spontzoug.server.service.IPlanOptionService;
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
@RequestMapping("/api/planoption")
public class PlanOptionController {
    @Autowired
    private IPlanOptionService planOptionService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PlanOption l) {  planOptionService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PlanOption> update(@RequestBody PlanOption m) {
        return planOptionService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return planOptionService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<PlanOption>> findById(@PathVariable("id") String id) {
        Mono<PlanOption> m = planOptionService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<PlanOption>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<PlanOption> findByBusinessid(@PathVariable("id") String id){
        return planOptionService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<PlanOption> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        log.info("++++++++++++++++++++ get plan option called:"+ date );
        return planOptionService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<PlanOption> findDynamics(
            @PathVariable("id") String id){
        return planOptionService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<PlanOption> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        log.info("++++++++++++++++++++ get plan option called:"+ date );
        return planOptionService.findDynamicsInc(id,date);
    }
}
