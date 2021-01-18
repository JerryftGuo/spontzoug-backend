package com.spontzoug.server.service;


import com.spontzoug.server.model.User;
import com.spontzoug.server.model.UserProfile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserProfileService {
    void create(UserProfile b);
    Mono<UserProfile> findById(final String id);
    Mono<UserProfile> update(UserProfile service);
    Mono<Void> deleteById(final String id);
    Mono<UserProfile> findByUserid(final String id);
    Flux<UserProfile> findByCreator(final String id);
    Flux<UserProfile> findByBusinessid(final String id);
}