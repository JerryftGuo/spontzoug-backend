package com.spontzoug.server.controller;

import com.spontzoug.server.model.User;
import com.spontzoug.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User l) {
        /*
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String myPassword = passwordEncoder.encode( l.getPassword());
        l.setPassword(myPassword);
        */
        userService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> update(@RequestBody User m) {
        return userService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return userService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<User>> findById(@PathVariable("id") String id) {
        Mono<User> m = userService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<User>>(m,s);
    }

    @GetMapping("/username/{name}")
    public Mono<User> findByUsername(@PathVariable("name") String name){
        return userService.findByUsername(name);
    }


    @GetMapping("/all")
    public Flux<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/all/{date}")
    public Flux<User> findAllInc(
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return userService.findAllInc(date);
    }

}
