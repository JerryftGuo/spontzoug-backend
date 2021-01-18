package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.Table;
import com.spontzoug.server.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/table")
public class TableController {
    @Autowired
    private ITableService tableService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Table l) {  tableService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Table> update(@RequestBody Table m) {
        return tableService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return tableService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Table>> findById(@PathVariable("id") String id) {
        Mono<Table> m = tableService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Table>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<Table> findByBusinessid(
            @PathVariable("id") String id){
        return tableService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<Table> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return tableService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<Table> findDynamics(
            @PathVariable("id") String id){
        return tableService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<Table> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return tableService.findDynamicsInc(id,date);
    }
}
