package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.Menu;
import com.spontzoug.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Menu l) {  menuService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Menu> update(@RequestBody Menu m) {
        return menuService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return menuService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Menu>> findById(@PathVariable("id") String id) {
        Mono<Menu> m = menuService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Menu>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<Menu> findByBusinessid(@PathVariable("id") String id){
        return menuService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<Menu> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return menuService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<Menu> findDynamics(
            @PathVariable("id") String id){
        return menuService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<Menu> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return menuService.findDynamicsInc(id,date);
    }
}
