package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.Service;
import com.spontzoug.server.service.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/service")
public class ServiceController {
    @Autowired
    private IServiceService serviceService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Service l) {  serviceService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Service> update(@RequestBody Service l) {
        return serviceService.update(l);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return serviceService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Service>> findById(@PathVariable("id") String id) {
        Mono<Service> l = serviceService.findById(id);
        HttpStatus s = l!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Service>>(l,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<Service> findByBusinessid(
            @PathVariable("id") String id){
        return serviceService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<Service> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return serviceService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<Service> findDynamics(
            @PathVariable("id") String id){
        return serviceService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<Service> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return serviceService.findDynamicsInc(id,date);
    }
}
