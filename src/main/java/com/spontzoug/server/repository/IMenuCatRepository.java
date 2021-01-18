package com.spontzoug.server.repository;

import com.spontzoug.server.model.MenuCat;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IMenuCatRepository extends ReactiveMongoRepository<MenuCat, String> {
    @Query(value="{'businessid':?0}")
    Flux<MenuCat> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<MenuCat> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0}")
    Flux<MenuCat> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<MenuCat> findDynamicsInc(final String id, final Date date);
}