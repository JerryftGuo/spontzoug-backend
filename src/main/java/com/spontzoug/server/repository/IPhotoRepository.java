package com.spontzoug.server.repository;

import com.spontzoug.server.model.Photo;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPhotoRepository extends ReactiveMongoRepository<Photo, String> {
    @Query("{'name':?0}")
    Mono<Photo> findByName(final String name);
    @Query("{'basename':?0}")
    Flux<Photo> findByBaseName(final String name);
}