package com.spontzoug.server.controller;

import com.spontzoug.server.model.Realtor;
import com.spontzoug.server.model.UserProfile;
import com.spontzoug.server.service.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/userprofile")
public class UserProfileController {
    @Autowired
    private IUserProfileService userProfileService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserProfile l) {
        /*
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String myPassword = passwordEncoder.encode( l.getPassword());
        l.setPassword(myPassword);
        */
        userProfileService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<UserProfile> update(@RequestBody UserProfile m) {
        return userProfileService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return userProfileService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<UserProfile>> findById(@PathVariable("id") String id) {
        Mono<UserProfile> m = userProfileService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<UserProfile>>(m,s);
    }
    @GetMapping("/userid/{id}")
    public ResponseEntity<Mono<UserProfile>> findByUserid(@PathVariable("id") String id) {
        Mono<UserProfile> m = userProfileService.findByUserid(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<UserProfile>>(m,s);
    }
    @GetMapping("/creator/{id}")
    public Flux<UserProfile> findByCreator(@PathVariable("id") String id) {
        return userProfileService.findByCreator(id);
    }
    @GetMapping("/businessconfig/{id}")
    public Flux<UserProfile> findByBusinessConf(@PathVariable("id") String id){
        return userProfileService.findByBusinessid(id);
    }
}
