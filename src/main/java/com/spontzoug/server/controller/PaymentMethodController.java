package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.Billing;
import com.spontzoug.server.model.PaymentMethod;
import com.spontzoug.server.service.IPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/paymentmethod")
public class PaymentMethodController {
    @Autowired
    private IPaymentMethodService paymentMethodService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PaymentMethod b){
        paymentMethodService.create(b);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PaymentMethod> update(@RequestBody PaymentMethod b){
        return  paymentMethodService.update(b);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById(@PathVariable("id") String id){
        return  paymentMethodService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<PaymentMethod>> findById(@PathVariable("id")  String id) {
        Mono<PaymentMethod> b = paymentMethodService.findById(id);
        HttpStatus s = b != null ? HttpStatus.OK: HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<PaymentMethod>>( b, s);
    }

    @GetMapping("/dynamic/{id}")
    public Flux<PaymentMethod> findDynamics(@PathVariable("id") String id){
        return paymentMethodService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<PaymentMethod> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return paymentMethodService.findDynamicsInc(id,date);
    }

    @GetMapping("/businessid/{id}")
    public Flux<PaymentMethod> findByBusinessId(@PathVariable("id") String id){
        return paymentMethodService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<PaymentMethod> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return paymentMethodService.findByBusinessidInc(id,date);
    }
}
