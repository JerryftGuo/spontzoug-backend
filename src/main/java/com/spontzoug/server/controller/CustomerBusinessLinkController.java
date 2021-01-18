package com.spontzoug.server.controller;

import com.spontzoug.server.model.CustomerBusinessLink;
import com.spontzoug.server.service.ICustomerBusinessLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/customerbusinesslink")
public class CustomerBusinessLinkController {
    @Autowired
    private ICustomerBusinessLinkService customerBusinessLinkService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CustomerBusinessLink l) {
        /*
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String myPassword = passwordEncoder.encode( l.getPassword());
        l.setPassword(myPassword);
        */
        customerBusinessLinkService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CustomerBusinessLink> update(@RequestBody CustomerBusinessLink m) {
        return customerBusinessLinkService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return customerBusinessLinkService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<CustomerBusinessLink>> findById(@PathVariable("id") String id) {
        Mono<CustomerBusinessLink> m = customerBusinessLinkService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<CustomerBusinessLink>>(m,s);
    }


    @GetMapping("/businessconfig/{id}")
    public Flux<CustomerBusinessLink> findByBusinessConfig(
            @PathVariable("id") String id
    ){
        return customerBusinessLinkService.findByBusinessid(id);
    }


}
