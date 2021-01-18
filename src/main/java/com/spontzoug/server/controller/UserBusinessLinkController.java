package com.spontzoug.server.controller;

import com.spontzoug.server.model.UserBusinessLink;
import com.spontzoug.server.service.IUserBusinessLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/userbusinesslink")
public class UserBusinessLinkController {
    @Autowired
    private IUserBusinessLinkService userBusinessLinkService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserBusinessLink l) {
        /*
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String myPassword = passwordEncoder.encode( l.getPassword());
        l.setPassword(myPassword);
        */
        userBusinessLinkService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<UserBusinessLink> update(@RequestBody UserBusinessLink m) {
        return userBusinessLinkService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return userBusinessLinkService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<UserBusinessLink>> findById(@PathVariable("id") String id) {
        Mono<UserBusinessLink> m = userBusinessLinkService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<UserBusinessLink>>(m,s);
    }

    @GetMapping("/all")
    public Flux<UserBusinessLink> findAll(){
        return userBusinessLinkService.findAll();
    }


    @GetMapping("/businessconfig/{id}")
    public Flux<UserBusinessLink> findByBusinessConfig(
            @PathVariable("id") String id
    ){
        return userBusinessLinkService.findByBusinessid(id);
    }

    @GetMapping("/all/{date}")
    public Flux<UserBusinessLink> findAllInc(
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return userBusinessLinkService.findAllInc(date);
    }

}
