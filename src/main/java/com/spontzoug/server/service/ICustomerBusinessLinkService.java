package com.spontzoug.server.service;


import com.spontzoug.server.model.Customer;
import com.spontzoug.server.model.CustomerBusinessLink;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ICustomerBusinessLinkService {
    void create(CustomerBusinessLink b);
    Mono<CustomerBusinessLink> findById(final String id);
    Mono<CustomerBusinessLink> update(CustomerBusinessLink link);
    Mono<Void> deleteById(final String id);
    Flux<CustomerBusinessLink> findByCustomerid(final String id);
    Flux<CustomerBusinessLink> findByBusinessid(final String bid);
}