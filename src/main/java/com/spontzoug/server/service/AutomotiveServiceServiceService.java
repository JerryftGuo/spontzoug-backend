package com.spontzoug.server.service;

import com.spontzoug.server.model.AutomotiveServiceService;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IAutomotiveServiceServiceRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class AutomotiveServiceServiceService implements IAutomotiveServiceServiceService {
    @Autowired
    private IAutomotiveServiceServiceRepository serviceRepository;
    @Autowired
    private ISseService sseService;

    public void create(AutomotiveServiceService service)
    {
        serviceRepository.save(service)
                .flatMap( ser -> {
                            return sseService.publishEnt( ser.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, ser.getBusinessid(), "automotiveserviceservice", "", "", ""));
                        }
                ).subscribe();
        //serviceRepository.save(service).subscribe();
    }

    public Mono<AutomotiveServiceService> update(AutomotiveServiceService service)
    {
        return sseService.publishEnt( service.getRegion(),
                new SseEvent(SseType.SSE_ENT, service.getBusinessid(), "automotiveserviceservice", "", "", "") )
                .flatMap( bb ->{
                    return serviceRepository.save(service);
                });
        //    return serviceRepository.save(service);
    }

    public Mono<Void> deleteById(final String id){
        return serviceRepository.deleteById(id);
    }

    public Mono<AutomotiveServiceService> findById(final String id){
        return serviceRepository.findById(id);
    }
    public Flux<AutomotiveServiceService> findByBusinessid(final String id) {
        return serviceRepository.findByBusinessid(id);
    }
    public Flux<AutomotiveServiceService> findByBusinessidInc(final String id,final Date date) {
        return serviceRepository.findByBusinessidInc(id,date);
    }
    public Flux<AutomotiveServiceService> findDynamics(final String id) {
        return serviceRepository.findDynamics(id);
    }
    public Flux<AutomotiveServiceService> findDynamicsInc(final String id, final Date date) {
        return serviceRepository.findDynamicsInc(id,date);
    }

    public Flux<AutomotiveServiceService> findByRegion(final String id) {
        return serviceRepository.findByRegion(id);
    }
    public Flux<AutomotiveServiceService> findByRegionInc(final String id, final Date date) {
        return serviceRepository.findByRegionInc(id,date);
    }

    public Mono<Long> countActive(final String id){
        return serviceRepository.countActive(id);
    }
}