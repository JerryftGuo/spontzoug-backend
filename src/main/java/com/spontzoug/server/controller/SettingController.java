package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.Setting;
import com.spontzoug.server.service.ISettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/setting")
public class SettingController {
    @Autowired
    private ISettingService settingService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Setting l) {  settingService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Setting> update(@RequestBody Setting m) {
        return settingService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return settingService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Setting>> findById(@PathVariable("id") String id) {
        Mono<Setting> m = settingService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Setting>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<Setting> findByBusinessid(
            @PathVariable("id") String id){
        return settingService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<Setting> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return settingService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<Setting> findDynamics(
            @PathVariable("id") String id){
        return settingService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<Setting> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return settingService.findDynamicsInc(id,date);
    }
}
