package com.spontzoug.server.service;

import com.spontzoug.server.model.PersonalCareService;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IPersonalCareServiceRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class PersonalCareServiceService implements IPersonalCareServiceService {
    @Autowired
    private IPersonalCareServiceRepository serviceRepository;
    @Autowired
    private ISseService sseService;

    public void create(PersonalCareService service)
    {
        serviceRepository.save(service)
                .flatMap( ser -> {
                            return sseService.publishEnt( ser.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, ser.getBusinessid(), "personalcareservice", "", "", ""));
                        }
                ).subscribe();
        //serviceRepository.save(service).subscribe();
    }

    public Mono<PersonalCareService> update(PersonalCareService service)
    {
        return sseService.publishEnt( service.getRegion(),
                new SseEvent(SseType.SSE_ENT, service.getBusinessid(), "personalcareservice", "", "", "") )
                .flatMap( bb ->{
                    return serviceRepository.save(service);
                });
        //    return serviceRepository.save(service);
    }

    public Mono<Void> deleteById(final String id){
        return serviceRepository.deleteById(id);
    }

    public Mono<PersonalCareService> findById(final String id){
        return serviceRepository.findById(id);
    }
    public Flux<PersonalCareService> findByBusinessid(final String id) {
        return serviceRepository.findByBusinessid(id);
    }
    public Flux<PersonalCareService> findByBusinessidInc(final String id,final Date date) {
        return serviceRepository.findByBusinessidInc(id,date);
    }
    public Flux<PersonalCareService> findDynamics(final String id) {
        return serviceRepository.findDynamics(id);
    }
    public Flux<PersonalCareService> findDynamicsInc(final String id, final Date date) {
        return serviceRepository.findDynamicsInc(id,date);
    }

    public Flux<PersonalCareService> findByRegion(final String id) {
        return serviceRepository.findByRegion(id);
    }
    public Flux<PersonalCareService> findByRegionInc(final String id, final Date date) {
        return serviceRepository.findByRegionInc(id,date);
    }
    public Mono<Long> countActive(final String id){
        return serviceRepository.countActive(id);
    }
}