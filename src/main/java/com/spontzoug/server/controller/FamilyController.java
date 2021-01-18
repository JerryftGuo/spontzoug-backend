package com.spontzoug.server.controller;

import com.spontzoug.server.http.PhoneCount;
import com.spontzoug.server.model.Family;
import com.spontzoug.server.service.IFamilyService;
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
@RequestMapping("/api/family")
public class FamilyController {
    @Autowired
    private IFamilyService familyService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Family l) {
        log.info("create family called");
     //   String phone = l.getPhone();
     //   Mono<Family> familyMono = familyService.findByPhone(phone);
     //   familyMono.switchIfEmpty( familyService.create(l);).subscribe()
        familyService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Family> update(@RequestBody Family l) {
        return familyService.update(l);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return familyService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Family>> findById(@PathVariable("id") String id) {
        Mono<Family> l = familyService.findById(id);
        HttpStatus s = l!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Family>>(l,s);
    }
/*
    @GetMapping("/phone/{phone}")
    public ResponseEntity<Mono<Family>> findByPhone(@PathVariable("phone") String phone) {
        Mono<Family> l = familyService.findByPhone(phone);
        HttpStatus s = l!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Family>>(l,s);
    }

 */

    @GetMapping("/firstname/{fisrtname}")
    public Flux<Family> findByFirtname(@PathVariable("firstname") String firstname){
        return familyService.findByBusinessid(firstname);
    }

    @GetMapping("/businessid/{id}")
    public Flux<Family> findByBusinessid(
            @PathVariable("id") String id){
        return familyService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<Family> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return familyService.findByBusinessidInc(id,date);
    }
    @GetMapping("/families/{id}")
    public Flux<Family> findFamilies(
            @PathVariable("id") String id) {
        return familyService.findFamilies(id);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<Family> findDynamics(
            @PathVariable("id") String id) {
        return familyService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<Family> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date) {
        return familyService.findDynamicsInc(id,date);
    }

    @GetMapping("/count/{businessid}/{phone}")
    public Mono<PhoneCount> countByBusinessIdAndPhone(
            @PathVariable("businessid") String id,
            @PathVariable("phone") String phone) {
        return familyService.countByBusinessIdAndPhone(id,phone)
                .map( cnt -> new PhoneCount(phone, cnt));
    }

}
