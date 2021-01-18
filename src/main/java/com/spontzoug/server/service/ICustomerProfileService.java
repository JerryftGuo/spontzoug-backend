package com.spontzoug.server.service;


import com.spontzoug.server.model.Customer;
import com.spontzoug.server.model.CustomerProfile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerProfileService {
    void create(CustomerProfile b);
    Mono<CustomerProfile> findById(final String id);
    Mono<CustomerProfile> update(CustomerProfile service);
    Mono<Void> deleteById(final String id);
    Mono<CustomerProfile> findByCustomerid(final String id);
    Flux<CustomerProfile> findByCreator(final String id);
    Flux<CustomerProfile> findByBusinessid(final String id);
}