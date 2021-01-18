package com.spontzoug.server.repository;

import com.spontzoug.server.model.CustomerDevice;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerDeviceRepository extends ReactiveMongoRepository<CustomerDevice, String> {
    @Query("{'customerid':?0}")
    Flux<CustomerDevice> findByCustomerid(final String id);
}