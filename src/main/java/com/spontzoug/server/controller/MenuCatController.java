package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.MenuCat;
import com.spontzoug.server.service.IMenuCatService;
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
@RequestMapping("/api/menu_cat")
public class MenuCatController {
    @Autowired
    private IMenuCatService menuCatService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody MenuCat l) {
        log.info("created menu cat called");
        menuCatService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<MenuCat> update(@RequestBody MenuCat m) {
        return menuCatService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return menuCatService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<MenuCat>> findById(@PathVariable("id") String id) {
        Mono<MenuCat> m = menuCatService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<MenuCat>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<MenuCat> findByBusinessid(@PathVariable("id") String id){
        log.info("get category called");
        return menuCatService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<MenuCat> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return menuCatService.findByBusinessidInc(id,date);
    }

    @GetMapping("/dynamic/{id}")
    public Flux<MenuCat> findDynamics(@PathVariable("id") String id){
        log.info("get category dynamic called");
        return menuCatService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<MenuCat> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return menuCatService.findDynamicsInc(id,date);
    }
}
