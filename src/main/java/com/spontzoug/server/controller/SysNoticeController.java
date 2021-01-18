package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.SysNotice;
import com.spontzoug.server.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/sysnotice")
public class SysNoticeController {
    @Autowired
    private ISysNoticeService sysnoticeService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody SysNotice l) {  sysnoticeService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<SysNotice> update(@RequestBody SysNotice m) {
        return sysnoticeService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return sysnoticeService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<SysNotice>> findById(@PathVariable("id") String id) {
        Mono<SysNotice> m = sysnoticeService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<SysNotice>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<SysNotice> findByBusinessid(
            @PathVariable("id") String id){
        return sysnoticeService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<SysNotice> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return sysnoticeService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<SysNotice> findDynamics(
            @PathVariable("id") String id){
        return sysnoticeService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<SysNotice> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return sysnoticeService.findDynamicsInc(id,date);
    }

    @GetMapping("/business")
    public Flux<SysNotice> findForBusiness(){
        return sysnoticeService.findForBusiness();
    }
    @GetMapping("/business/{date}")
    public Flux<SysNotice> findForBusinessInc(
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return sysnoticeService.findForBusinessInc(date);
    }
}
