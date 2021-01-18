package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.TrainingSetting;
import com.spontzoug.server.model.TrainingSetting;
import com.spontzoug.server.service.ITrainingSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/trainingsetting")
public class TrainingSettingController {
    @Autowired
    private ITrainingSettingService trainingSettingService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody TrainingSetting l) {  trainingSettingService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<TrainingSetting> update(@RequestBody TrainingSetting m) {
        return trainingSettingService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return trainingSettingService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<TrainingSetting>> findById(@PathVariable("id") String id) {
        Mono<TrainingSetting> m = trainingSettingService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<TrainingSetting>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<TrainingSetting> findByBusinessid(@PathVariable("id") String id){
        return trainingSettingService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<TrainingSetting> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return trainingSettingService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<TrainingSetting> findDynamics(@PathVariable("id") String id){
        return trainingSettingService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<TrainingSetting> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return trainingSettingService.findDynamicsInc(id,date);
    }

    @GetMapping("/all")
    public Flux<TrainingSetting> findAll(){
        return trainingSettingService.findAll();
    }

    @GetMapping("/all/{date}")
    public Flux<TrainingSetting> findAllInc(
            @PathVariable("date") Date date  ){
        return trainingSettingService.findAllInc(date);
    }

    @GetMapping("/region/{region}")
    public Flux<TrainingSetting> findByRegion(
            @PathVariable("region") String region
    ){
        return trainingSettingService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<TrainingSetting> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return trainingSettingService.findByRegionInc(region,date);
    }
}
