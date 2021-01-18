package com.spontzoug.server.service;

import com.spontzoug.server.model.AutomativeServiceService;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IAutomativeServiceServiceRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class AutomativeServiceServiceService implements IAutomativeServiceServiceService {
    @Autowired
    private IAutomativeServiceServiceRepository serviceRepository;
    @Autowired
    private ISseService sseService;

    public void create(AutomativeServiceService service)
    {
        serviceRepository.save(service)
                .flatMap( ser -> {
                            return sseService.publishEnt( ser.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, ser.getBusinessid(), "automativeserviceservice", "", "", ""));
                        }
                ).subscribe();
        //serviceRepository.save(service).subscribe();
    }

    public Mono<AutomativeServiceService> update(AutomativeServiceService service)
    {
        return sseService.publishEnt( service.getRegion(),
                new SseEvent(SseType.SSE_ENT, service.getBusinessid(), "automativeserviceservice", "", "", "") )
                .flatMap( bb ->{
                    return serviceRepository.save(service);
                });
        //    return serviceRepository.save(service);
    }

    public Mono<Void> deleteById(final String id){
        return serviceRepository.deleteById(id);
    }

    public Mono<AutomativeServiceService> findById(final String id){
        return serviceRepository.findById(id);
    }
    public Flux<AutomativeServiceService> findByBusinessid(final String id) {
        return serviceRepository.findByBusinessid(id);
    }
    public Flux<AutomativeServiceService> findByBusinessidInc(final String id,final Date date) {
        return serviceRepository.findByBusinessidInc(id,date);
    }
    public Flux<AutomativeServiceService> findDynamics(final String id) {
        return serviceRepository.findDynamics(id);
    }
    public Flux<AutomativeServiceService> findDynamicsInc(final String id, final Date date) {
        return serviceRepository.findDynamicsInc(id,date);
    }

    public Flux<AutomativeServiceService> findByRegion(final String id) {
        return serviceRepository.findByRegion(id);
    }
    public Flux<AutomativeServiceService> findByRegionInc(final String id, final Date date) {
        return serviceRepository.findByRegionInc(id,date);
    }

    public Mono<Long> countActive(final String id){
        return serviceRepository.countActive(id);
    }
}