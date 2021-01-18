package com.spontzoug.server.service;

import com.spontzoug.server.model.Service;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IServiceRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@org.springframework.stereotype.Service
public class ServiceService implements IServiceService {
    @Autowired
    private IServiceRepository serviceRepository;
    @Autowired
    private ISseService sseService;

    public void create(Service service)
    {
        serviceRepository.save(service)
                .flatMap( ser -> {
                            return sseService.publishEnt( ser.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, ser.getBusinessid(), "service", "", "", ""));
                        }
                ).subscribe();
        //serviceRepository.save(service).subscribe();
    }

    public Mono<Service> update(Service service)
    {
        return sseService.publishEnt( service.getRegion(),
                new SseEvent(SseType.SSE_ENT, service.getBusinessid(), "service", "", "", "") )
                .flatMap( bb ->{
                    return serviceRepository.save(service);
                });
    //    return serviceRepository.save(service);
    }

    public Mono<Void> deleteById(final String id){
        return serviceRepository.deleteById(id);
    }

    public Mono<Service> findById(final String id){
        return serviceRepository.findById(id);
    }
    public Flux<Service> findByBusinessid(final String id) {
        return serviceRepository.findByBusinessid(id);
    }
    public Flux<Service> findByBusinessidInc(final String id,final Date date) {
        return serviceRepository.findByBusinessidInc(id,date);
    }
    public Flux<Service> findDynamics(final String id) {
        return serviceRepository.findDynamics(id);
    }
    public Flux<Service> findDynamicsInc(final String id, final Date date) {
        return serviceRepository.findDynamicsInc(id,date);
    }
}