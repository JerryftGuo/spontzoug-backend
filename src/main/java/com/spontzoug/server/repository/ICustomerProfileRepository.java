package com.spontzoug.server.repository;

import com.spontzoug.server.model.Customer;
import com.spontzoug.server.model.CustomerProfile;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerProfileRepository extends ReactiveMongoRepository<CustomerProfile, String> {
    @Query("{'customerid':?0}")
    Mono<CustomerProfile> findByCustomerid(final String id);
    @Query("{'customerid':?0}")
    Flux<CustomerProfile> findByCreator(final String id);
    @Query("{'customerid':?0}")
    Flux<CustomerProfile> findByAdmin(final String id);
}