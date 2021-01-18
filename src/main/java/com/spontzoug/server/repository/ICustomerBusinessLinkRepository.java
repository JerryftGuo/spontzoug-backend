package com.spontzoug.server.repository;

import com.spontzoug.server.model.Customer;
import com.spontzoug.server.model.CustomerBusinessLink;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ICustomerBusinessLinkRepository extends ReactiveMongoRepository<CustomerBusinessLink, String> {
    @Query("{'customerid':?0}")
    Flux<CustomerBusinessLink> findByCustomerid(final String id);
    @Query("{'businessid':?0}")
    Flux<CustomerBusinessLink> findByBusinessid(final String bid);
}