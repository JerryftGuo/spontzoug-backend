package com.spontzoug.server.service;

import com.spontzoug.server.model.RealEstateService;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IRealEstateServiceRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class RealEstateServiceService implements IRealEstateServiceService {
    @Autowired
    private IRealEstateServiceRepository serviceRepository;
    @Autowired
    private ISseService sseService;

    public void create(RealEstateService service)
    {
        serviceRepository.save(service)
                .flatMap( ser -> {
                            return sseService.publishEnt( ser.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, ser.getBusinessid(), "realestateservice", "", "", ""));
                        }
                ).subscribe();
        //serviceRepository.save(service).subscribe();
    }

    public Mono<RealEstateService> update(RealEstateService service)
    {
        return sseService.publishEnt( service.getRegion(),
                new SseEvent(SseType.SSE_ENT, service.getBusinessid(), "realestateservice", "", "", "") )
                .flatMap( bb ->{
                    return serviceRepository.save(service);
                });
        //    return serviceRepository.save(service);
    }

    public Mono<Void> deleteById(final String id){
        return serviceRepository.deleteById(id);
    }

    public Mono<RealEstateService> findById(final String id){
        return serviceRepository.findById(id);
    }
    public Flux<RealEstateService> findByBusinessid(final String id) {
        return serviceRepository.findByBusinessid(id);
    }
    public Flux<RealEstateService> findByBusinessidInc(final String id,final Date date) {
        return serviceRepository.findByBusinessidInc(id,date);
    }
    public Flux<RealEstateService> findDynamics(final String id) {
        return serviceRepository.findDynamics(id);
    }
    public Flux<RealEstateService> findDynamicsInc(final String id, final Date date) {
        return serviceRepository.findDynamicsInc(id,date);
    }

    public Flux<RealEstateService> findByRegion(final String id) {
        return serviceRepository.findByRegion(id);
    }
    public Flux<RealEstateService> findByRegionInc(final String id, final Date date) {
        return serviceRepository.findByRegionInc(id,date);
    }
    public Mono<Long> countActive(final String id){
        return serviceRepository.countActive(id);
    }
}