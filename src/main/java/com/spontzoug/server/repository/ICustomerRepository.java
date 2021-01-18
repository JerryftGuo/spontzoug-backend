package com.spontzoug.server.repository;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.Customer;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ICustomerRepository extends ReactiveMongoRepository<Customer, String> {
    @Query("{'username':?0}")
    Mono<Customer> findByCustomername(final String name);
    @Query("{'email':?0}")
    Mono<Customer> findByEmail(final String name);
    @Query("{'$or':[{'username':?0}, {'email':?1}]}")
    Flux<Customer> findByCustomernameOrEmail(final String user, final String email);
    @Query(value = "{}",fields="{'password':0,'token':0}" )
    Flux<Customer> findAll();
    @Query(value="{'modified':{'$gt':?0 }",fields="{'password':0,'token':0}" )
    Flux<Customer> findAllInc(final Date date );
}