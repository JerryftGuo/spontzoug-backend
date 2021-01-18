package com.spontzoug.server.controller;

import com.spontzoug.server.model.Realtor;
import com.spontzoug.server.model.CustomerProfile;
import com.spontzoug.server.service.ICustomerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/customerprofile")
public class CustomerProfileController {
    @Autowired
    private ICustomerProfileService customerProfileService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CustomerProfile l) {
        /*
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String myPassword = passwordEncoder.encode( l.getPassword());
        l.setPassword(myPassword);
        */
        customerProfileService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CustomerProfile> update(@RequestBody CustomerProfile m) {
        return customerProfileService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return customerProfileService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<CustomerProfile>> findById(@PathVariable("id") String id) {
        Mono<CustomerProfile> m = customerProfileService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<CustomerProfile>>(m,s);
    }
    @GetMapping("/customerid/{id}")
    public ResponseEntity<Mono<CustomerProfile>> findByCustomerid(@PathVariable("id") String id) {
        Mono<CustomerProfile> m = customerProfileService.findByCustomerid(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<CustomerProfile>>(m,s);
    }
    @GetMapping("/creator/{id}")
    public Flux<CustomerProfile> findByCreator(@PathVariable("id") String id) {
        return customerProfileService.findByCreator(id);
    }
    @GetMapping("/businessconfig/{id}")
    public Flux<CustomerProfile> findByBusinessConf(@PathVariable("id") String id){
        return customerProfileService.findByBusinessid(id);
    }
}
