package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.Notice;
import com.spontzoug.server.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {
    @Autowired
    private INoticeService noticeService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Notice l) {  noticeService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Notice> update(@RequestBody Notice m) {
        return noticeService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return noticeService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Notice>> findById(@PathVariable("id") String id) {
        Mono<Notice> m = noticeService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Notice>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<Notice> findByBusinessid(
            @PathVariable("id") String id){
        return noticeService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<Notice> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return noticeService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<Notice> findDynamics(
            @PathVariable("id") String id){
        return noticeService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<Notice> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return noticeService.findDynamicsInc(id,date);
    }
}
