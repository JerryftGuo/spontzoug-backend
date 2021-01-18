package com.spontzoug.server.service;

import com.spontzoug.server.model.AutomativeSaleService;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IAutomativeSaleServiceRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class AutomativeSaleServiceService implements IAutomativeSaleServiceService {
    @Autowired
    private IAutomativeSaleServiceRepository serviceRepository;
    @Autowired
    private ISseService sseService;

    public void create(AutomativeSaleService service)
    {
        serviceRepository.save(service)
                .flatMap( ser -> {
                            return sseService.publishEnt( ser.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, ser.getBusinessid(), "automativesaleservice", "", "", ""));
                        }
                ).subscribe();
        //serviceRepository.save(service).subscribe();
    }

    public Mono<AutomativeSaleService> update(AutomativeSaleService service)
    {
        return sseService.publishEnt( service.getRegion(),
                new SseEvent(SseType.SSE_ENT, service.getBusinessid(), "automativesaleservice", "", "", "") )
                .flatMap( bb ->{
                    return serviceRepository.save(service);
                });
        //    return serviceRepository.save(service);
    }

    public Mono<Void> deleteById(final String id){
        return serviceRepository.deleteById(id);
    }

    public Mono<AutomativeSaleService> findById(final String id){
        return serviceRepository.findById(id);
    }
    public Flux<AutomativeSaleService> findByBusinessid(final String id) {
        return serviceRepository.findByBusinessid(id);
    }
    public Flux<AutomativeSaleService> findByBusinessidInc(final String id,final Date date) {
        return serviceRepository.findByBusinessidInc(id,date);
    }
    public Flux<AutomativeSaleService> findDynamics(final String id) {
        return serviceRepository.findDynamics(id);
    }
    public Flux<AutomativeSaleService> findDynamicsInc(final String id, final Date date) {
        return serviceRepository.findDynamicsInc(id,date);
    }

    public Flux<AutomativeSaleService> findByRegion(final String id) {
        return serviceRepository.findByRegion(id);
    }
    public Flux<AutomativeSaleService> findByRegionInc(final String id, final Date date) {
        return serviceRepository.findByRegionInc(id,date);
    }

    public Mono<Long> countActive(final String id){
        return serviceRepository.countActive(id);
    }
}