package com.spontzoug.server.repository;

import com.spontzoug.server.model.Location;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface ILocationRepository extends ReactiveMongoRepository<Location, String> {
    @Query(value="{'businessid': ?0}")
    Flux<Location> findByBusinessid(final String id );
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}")
    Flux<Location> findByBusinessidInc(final String id, final Date date);
    @Query(value="{ 'address.geolocation': {'$near':[?0.x, ?0.y], '$maxDistance': ?1}}")
    Flux<Location> findByGeolocation(final Point point, final Distance distance);
    @Query(value="{'businessid':?0}", fields="{'created':0, 'modified':0}")
    Flux<Location> findDynamics(final String id);
    @Query(value="{'businessid':?0,'modified':{'$gt':?1}}", fields="{'created':0, 'modified':0}")
    Flux<Location> findDynamicsInc(final String id, final Date date);
}