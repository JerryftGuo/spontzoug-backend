package com.spontzoug.server.service;

import com.spontzoug.server.model.HealthService;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IHealthServiceRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class HealthServiceService implements IHealthServiceService {
    @Autowired
    private IHealthServiceRepository serviceRepository;
    @Autowired
    private ISseService sseService;

    public void create(HealthService service)
    {
        serviceRepository.save(service)
                .flatMap( ser -> {
                            return sseService.publishEnt( ser.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, ser.getBusinessid(), "service", "", "", ""));
                        }
                ).subscribe();
        //serviceRepository.save(service).subscribe();
    }

    public Mono<HealthService> update(HealthService service)
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

    public Mono<HealthService> findById(final String id){
        return serviceRepository.findById(id);
    }
    public Flux<HealthService> findByBusinessid(final String id) {
        return serviceRepository.findByBusinessid(id);
    }
    public Flux<HealthService> findByBusinessidInc(final String id,final Date date) {
        return serviceRepository.findByBusinessidInc(id,date);
    }
    public Flux<HealthService> findDynamics(final String id) {
        return serviceRepository.findDynamics(id);
    }
    public Flux<HealthService> findDynamicsInc(final String id, final Date date) {
        return serviceRepository.findDynamicsInc(id,date);
    }

    public Flux<HealthService> findByRegion(final String id) {
        return serviceRepository.findByRegion(id);
    }
    public Flux<HealthService> findByRegionInc(final String id, final Date date) {
        return serviceRepository.findByRegionInc(id,date);
    }
    public Mono<Long> countActive(final String id){
        return serviceRepository.countActive(id);
    }
}