package com.spontzoug.server.repository;

import com.spontzoug.server.model.Promotion;
import com.spontzoug.server.model.Room;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IRoomRepository extends ReactiveMongoRepository<Room, String> {
    @Query(value="{'businessid':?0}")
    Flux<Room> findByBusinessid(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Room> findByBusinessidInc(final String id, final Date date);
    @Query(value="{'businessid':?0}")
    Flux<Room> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Room> findDynamicsInc(final String id, final Date date);
}