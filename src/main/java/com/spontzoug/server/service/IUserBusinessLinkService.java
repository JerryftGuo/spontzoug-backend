package com.spontzoug.server.service;


import com.spontzoug.server.model.User;
import com.spontzoug.server.model.UserBusinessLink;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IUserBusinessLinkService {
    void create(UserBusinessLink b);
    Mono<UserBusinessLink> findById(final String id);
    Mono<UserBusinessLink> update(UserBusinessLink link);
    Mono<Void> deleteById(final String id);
    Flux<UserBusinessLink> findByUserid(final String id);
    Flux<UserBusinessLink> findAllInc(final Date date);
    Flux<UserBusinessLink> findAll();
    Mono<UserBusinessLink> findByBusinessStaffid(final String bid, final String sid);
    Flux<UserBusinessLink> findByBusinessid(final String bid);
}