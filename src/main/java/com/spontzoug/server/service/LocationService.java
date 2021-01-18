package com.spontzoug.server.service;

import com.spontzoug.server.model.Location;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.ILocationRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;


@Service
public  class LocationService implements ILocationService {
    @Autowired
    private ILocationRepository locationRepository;
    @Autowired
    private ISseService sseService;

    public void create(Location location)
    {
        locationRepository.save(location)
                .flatMap( biz -> {
                            return sseService.publishEnt( biz.getAddress().getProvince(),
                                    new SseEvent(SseType.SSE_ENT, biz.getBusinessid(), "location", "", "", ""));
                        }
                ).subscribe();
    //    locationRepository.save(location).subscribe();
    }
    public Mono<Location> update(Location location)
    {
        return sseService.publishEnt( location.getAddress().getProvince(),
                new SseEvent(SseType.SSE_ENT, location.getBusinessid(), "location", "", "", "") )
                .flatMap( bb ->{
                    return locationRepository.save(location);
                });

        //    return locationRepository.save(location);
    }
    public Mono<Void> deleteById(final String id) {
        return locationRepository.deleteById(id);
    }

    public Mono<Location> findById(final String id) {
        return locationRepository.findById(id);
    }

    public Flux<Location> findByBusinessid(final  String id){
        return locationRepository.findByBusinessid(id);
    }
    public Flux<Location> findByBusinessidInc(final  String id,final Date date){
        return locationRepository.findByBusinessidInc(id,date);
    }

    public Flux<Location> findByGeolocation(final Point point, final Distance distance){
        return locationRepository.findByGeolocation(point,distance);
    }

    public Flux<Location> findDynamics(final String id){
        return locationRepository.findDynamics(id);
    }
    public Flux<Location> findDynamicsInc(final String id, final Date date){
        return locationRepository.findDynamicsInc(id,date);
    }
}