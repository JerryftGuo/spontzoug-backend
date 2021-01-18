package com.spontzoug.server.repository;

import com.spontzoug.server.model.User;
import com.spontzoug.server.model.UserBusinessLink;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IUserBusinessLinkRepository extends ReactiveMongoRepository<UserBusinessLink, String> {
    @Query("{'userid':?0}")
    Flux<UserBusinessLink> findByUserid(final String id);
    @Query("{'role.businessid':?0}")
    Flux<UserBusinessLink> findByBusinessid(final String bid);
    @Query("{'role.businessid':?0,'role.staffid':?1}")
    Mono<UserBusinessLink> findByBusinessStaffid(final String bid, final String sid);
    @Query(value = "{}" )
    Flux<UserBusinessLink> findAll();
    @Query(value="{'created':{'$gt':?0 }")
    Flux<UserBusinessLink> findAllInc(final Date date );
}