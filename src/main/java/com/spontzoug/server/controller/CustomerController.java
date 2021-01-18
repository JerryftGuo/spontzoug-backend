package com.spontzoug.server.controller;

import com.spontzoug.server.model.Customer;
import com.spontzoug.server.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Customer l) {
        /*
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String myPassword = passwordEncoder.encode( l.getPassword());
        l.setPassword(myPassword);
        */
        customerService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Customer> update(@RequestBody Customer m) {
        return customerService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return customerService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Customer>> findById(@PathVariable("id") String id) {
        Mono<Customer> m = customerService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Customer>>(m,s);
    }

    @GetMapping("/customername/{name}")
    public Mono<Customer> findByCustomername(@PathVariable("name") String name){
        return customerService.findByCustomername(name);
    }


    @GetMapping("/all")
    public Flux<Customer> findAll(){
        return customerService.findAll();
    }

    @GetMapping("/all/{date}")
    public Flux<Customer> findAllInc(
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return customerService.findAllInc(date);
    }

}
