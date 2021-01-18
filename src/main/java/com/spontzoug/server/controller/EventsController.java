package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.Events;
import com.spontzoug.server.service.IEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/events")
public class EventsController {
    @Autowired
    private IEventsService eventsService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Events l) {  eventsService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Events> update(@RequestBody Events l) {
        return eventsService.update(l);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return eventsService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Events>> findById(@PathVariable("id") String id) {
        Mono<Events> l = eventsService.findById(id);
        HttpStatus s = l!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Events>>(l,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<Events> findByBusinessid(@PathVariable("id") String id){
        return eventsService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<Events> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return eventsService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<Events> findDynamics(
            @PathVariable("id") String id){
        return eventsService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<Events> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return eventsService.findDynamicsInc(id,date);
    }

    @GetMapping("/region/{region}")
    public Flux<Events> findByRegion(@PathVariable("region") String region){
        return eventsService.findByRegion(region);
    }
    @GetMapping("/region/{region}/{date}")
    public Flux<Events> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return eventsService.findByRegionInc(region,date);
    }
}
