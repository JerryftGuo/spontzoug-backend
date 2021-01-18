package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.TrainingClasses;
import com.spontzoug.server.service.ITrainingClassesService;
import com.spontzoug.server.service.ITrainingClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/trainingclasses")
public class TrainingClassesController {
    @Autowired
    private ITrainingClassesService trainingService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody TrainingClasses l) {  trainingService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<TrainingClasses> update(@RequestBody TrainingClasses l) {
        return trainingService.update(l);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return trainingService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<TrainingClasses>> findById(@PathVariable("id") String id) {
        Mono<TrainingClasses> l = trainingService.findById(id);
        HttpStatus s = l!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<TrainingClasses>>(l,s);
    }
    @GetMapping("/businessconfig/{id}")
    public Mono<Long> findByBusinessConf(@PathVariable("id") String id) {
        return trainingService.countActive(id);
    }
    @GetMapping("/businessid/{id}")
    public Flux<TrainingClasses> findByBusinessid(@PathVariable("id") String id){
        return trainingService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<TrainingClasses> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return trainingService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<TrainingClasses> findDynamics(
            @PathVariable("id") String id){
        return trainingService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<TrainingClasses> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return trainingService.findDynamicsInc(id,date);
    }

    @GetMapping("/region/{region}")
    public Flux<TrainingClasses> findByRegion(@PathVariable("region") String region){
        return trainingService.findByRegion(region);
    }
    @GetMapping("/region/{region}/{date}")
    public Flux<TrainingClasses> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return trainingService.findByRegionInc(region,date);
    }
}
