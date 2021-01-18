package com.spontzoug.server.service;

import com.spontzoug.server.model.AutomotiveSaleService;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IAutomotiveSaleServiceRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class AutomotiveSaleServiceService implements IAutomotiveSaleServiceService {
    @Autowired
    private IAutomotiveSaleServiceRepository serviceRepository;
    @Autowired
    private ISseService sseService;

    public void create(AutomotiveSaleService service)
    {
        serviceRepository.save(service)
                .flatMap( ser -> {
                            return sseService.publishEnt( ser.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, ser.getBusinessid(), "automotivesaleservice", "", "", ""));
                        }
                ).subscribe();
        //serviceRepository.save(service).subscribe();
    }

    public Mono<AutomotiveSaleService> update(AutomotiveSaleService service)
    {
        return sseService.publishEnt( service.getRegion(),
                new SseEvent(SseType.SSE_ENT, service.getBusinessid(), "automotivesaleservice", "", "", "") )
                .flatMap( bb ->{
                    return serviceRepository.save(service);
                });
        //    return serviceRepository.save(service);
    }

    public Mono<Void> deleteById(final String id){
        return serviceRepository.deleteById(id);
    }

    public Mono<AutomotiveSaleService> findById(final String id){
        return serviceRepository.findById(id);
    }
    public Flux<AutomotiveSaleService> findByBusinessid(final String id) {
        return serviceRepository.findByBusinessid(id);
    }
    public Flux<AutomotiveSaleService> findByBusinessidInc(final String id,final Date date) {
        return serviceRepository.findByBusinessidInc(id,date);
    }
    public Flux<AutomotiveSaleService> findDynamics(final String id) {
        return serviceRepository.findDynamics(id);
    }
    public Flux<AutomotiveSaleService> findDynamicsInc(final String id, final Date date) {
        return serviceRepository.findDynamicsInc(id,date);
    }

    public Flux<AutomotiveSaleService> findByRegion(final String id) {
        return serviceRepository.findByRegion(id);
    }
    public Flux<AutomotiveSaleService> findByRegionInc(final String id, final Date date) {
        return serviceRepository.findByRegionInc(id,date);
    }

    public Mono<Long> countActive(final String id){
        return serviceRepository.countActive(id);
    }
}