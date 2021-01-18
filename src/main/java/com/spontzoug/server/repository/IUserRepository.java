package com.spontzoug.server.repository;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IUserRepository extends ReactiveMongoRepository<User, String> {
    @Query("{'username':?0}")
    Mono<User> findByUsername(final String name);
    @Query("{'email':?0}")
    Mono<User> findByEmail(final String name);
    @Query("{'$or':[{'username':?0}, {'email':?1}]}")
    Flux<User> findByUsernameOrEmail(final String user, final String email);
    @Query(value = "{}",fields="{'password':0,'token':0}" )
    Flux<User> findAll();
    @Query(value="{'modified':{'$gt':?0 }",fields="{'password':0,'token':0}" )
    Flux<User> findAllInc(final Date date );
}