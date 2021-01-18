package com.spontzoug.server.service;


import com.spontzoug.server.model.Assistant;
import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ICustomerService {
    void create(Customer b);
    Mono<Customer> findById(final String id);
    Mono<Customer> update(Customer service);
    Mono<Void> deleteById(final String id);
    Mono<Customer> findByCustomername(final String username);
    Flux<Customer> findAllInc(final Date date);
    Flux<Customer> findAll();
}