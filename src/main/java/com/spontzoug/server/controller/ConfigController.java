package com.spontzoug.server.controller;

import com.spontzoug.server.annotation.IsUserAuthenticationEntAdmin;
import com.spontzoug.server.model.Config;
import com.spontzoug.server.service.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/config")
public class ConfigController {
    @Autowired
    private IConfigService configService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Config l) {
        /*
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String myPassword = passwordEncoder.encode( l.getPassword());
        l.setPassword(myPassword);
        */
        configService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Config> update(@RequestBody Config m) {
        return configService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return configService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Config>> findById(@PathVariable("id") String id) {
        Mono<Config> m = configService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Config>>(m,s);
    }

    @GetMapping("/prod/{name}")
    @IsUserAuthenticationEntAdmin
    public Mono<Config> findProductByName(@PathVariable("name") String name){
        return configService.findProductByName(name);
    }

    @GetMapping("/beta/{name}")
    public Mono<Config> findBetaByName(@PathVariable("name") String name){
        return configService.findBetaByName(name);
    }

    @GetMapping("/prod/inc/{name}/{date}")
    public Mono<Config> findProductIncByName(
            @PathVariable("name") String name,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return configService.findProductIncByName(name, date);
    }

    @GetMapping("/beta/inc/{name}/{date}")
    public Mono<Config> findBetaIncByName(
            @PathVariable("name") String name,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return configService.findBetaIncByName(name, date);
    }

    @GetMapping("/industry/{name}")
    public Flux<Config> findIndustryByName(
            @PathVariable("name") String name ){
        return configService.findIndustryByName(name);
    }
    @GetMapping("/all")
    public Flux<Config> findIndustryAll(){
        return configService.findIndustryAll();
    }

    @GetMapping("/industry/{name}/{date}")
    public Flux<Config> findIndustryIncByName(
            @PathVariable("name") String name,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return configService.findIndustryIncByName(name, date);
    }
    @GetMapping("/all/{date}")
    public Flux<Config> findIndustryIncAll(
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return configService.findIndustryIncAll(date);
    }
}
