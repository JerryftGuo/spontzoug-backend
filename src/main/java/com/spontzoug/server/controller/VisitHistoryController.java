package com.spontzoug.server.controller;

import com.spontzoug.server.model.VisitHistory;
import com.spontzoug.server.service.IVisitHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Calendar;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/visithistory")
public class VisitHistoryController {
    @Autowired
    private IVisitHistoryService visitHistoryService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody VisitHistory l) {
        visitHistoryService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<VisitHistory> update(@RequestBody VisitHistory m) {
        log.info("put visitHistory");
        return visitHistoryService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById(@PathVariable("id") String id) {
        log.info("del visitHistory");
        return visitHistoryService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<VisitHistory>> findById(@PathVariable("id") String id) {
        Mono<VisitHistory> m = visitHistoryService.findById(id);
        HttpStatus s = m != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<VisitHistory>>(m, s);
    }

    @GetMapping("/find/{bid}/{fid}/{mid}")
    public Flux<VisitHistory> findByBusinessFamilyId(
            @PathVariable("bid") String bid,
            @PathVariable("fid") String fid,
            @PathVariable("mid") String mid ) {
        return visitHistoryService.findByBusinessFamilyId(bid, fid, mid);
    }

    @GetMapping("/find/inc/{bid}/{fid}/{mid}/{date}")
    public Flux<VisitHistory> findByBusinessFamilyIdInc(
            @PathVariable("bid") String bid,
            @PathVariable("fid") String fid,
            @PathVariable("mid") String mid,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dt) {
        return visitHistoryService.findByBusinessFamilyIdInc(bid,fid, mid, dt);
    }

}
