package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.Location;
import com.spontzoug.server.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    @Autowired
    private ILocationService locationService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Location l) {  locationService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Location> update(@RequestBody Location l) {
        return locationService.update(l);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return locationService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Location>> findById(@PathVariable("id") String id) {
        Mono<Location> l = locationService.findById(id);
        HttpStatus s = l!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Location>>(l,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<Location> findByBusinessId(@PathVariable("id") String id){
        return locationService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<Location> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return locationService.findByBusinessidInc(id,date);
    }

    @GetMapping("/dynamic/{id}")
    public Flux<Location> findDynamics(
            @PathVariable("id") String id){
        return locationService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<Location> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return locationService.findDynamicsInc(id,date);
    }

}
