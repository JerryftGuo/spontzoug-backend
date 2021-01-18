package com.spontzoug.server.repository;

import com.spontzoug.server.model.UserDevice;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserDeviceRepository extends ReactiveMongoRepository<UserDevice, String> {
    @Query("{'userid':?0}")
    Flux<UserDevice> findByUserid(final String id);
}