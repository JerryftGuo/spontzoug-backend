package com.spontzoug.server.repository;

import com.spontzoug.server.model.User;
import com.spontzoug.server.model.UserProfile;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserProfileRepository extends ReactiveMongoRepository<UserProfile, String> {
    @Query("{'userid':?0}")
    Mono<UserProfile> findByUserid(final String id);
    @Query("{'userid':?0}")
    Flux<UserProfile> findByCreator(final String id);
    @Query("{'userid':?0}")
    Flux<UserProfile> findByAdmin(final String id);
}