package com.spontzoug.server.service;

import com.spontzoug.server.model.ResidentialService;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IResidentialServiceRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class ResidentialServiceService implements IResidentialServiceService {
    @Autowired
    private IResidentialServiceRepository serviceRepository;
    @Autowired
    private ISseService sseService;

    public void create(ResidentialService service)
    {
        serviceRepository.save(service)
                .flatMap( ser -> {
                            return sseService.publishEnt( ser.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, ser.getBusinessid(), "residentialservice", "", "", ""));
                        }
                ).subscribe();
        //serviceRepository.save(service).subscribe();
    }

    public Mono<ResidentialService> update(ResidentialService service)
    {
        return sseService.publishEnt( service.getRegion(),
                new SseEvent(SseType.SSE_ENT, service.getBusinessid(), "residentialservice", "", "", "") )
                .flatMap( bb ->{
                    return serviceRepository.save(service);
                });
        //    return serviceRepository.save(service);
    }

    public Mono<Void> deleteById(final String id){
        return serviceRepository.deleteById(id);
    }

    public Mono<ResidentialService> findById(final String id){
        return serviceRepository.findById(id);
    }
    public Flux<ResidentialService> findByBusinessid(final String id) {
        return serviceRepository.findByBusinessid(id);
    }
    public Flux<ResidentialService> findByBusinessidInc(final String id,final Date date) {
        return serviceRepository.findByBusinessidInc(id,date);
    }
    public Flux<ResidentialService> findDynamics(final String id) {
        return serviceRepository.findDynamics(id);
    }
    public Flux<ResidentialService> findDynamicsInc(final String id, final Date date) {
        return serviceRepository.findDynamicsInc(id,date);
    }

    public Flux<ResidentialService> findByRegion(final String id) {
        return serviceRepository.findByRegion(id);
    }
    public Flux<ResidentialService> findByRegionInc(final String id, final Date date) {
        return serviceRepository.findByRegionInc(id,date);
    }
    public Mono<Long> countActive(final String id){
        return serviceRepository.countActive(id);
    }
}