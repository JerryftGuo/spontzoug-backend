package com.spontzoug.server.service;

import com.spontzoug.server.model.TrainingClasses;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.ITrainingClassesRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class TrainingClassesService implements ITrainingClassesService {
    @Autowired
    private ITrainingClassesRepository serviceRepository;
    @Autowired
    private ISseService sseService;

    public void create(TrainingClasses service)
    {
        serviceRepository.save(service)
                .flatMap( ser -> {
                            return sseService.publishEnt( ser.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, ser.getBusinessid(), "trainingclasses", "", "", ""));
                        }
                ).subscribe();
        //serviceRepository.save(service).subscribe();
    }

    public Mono<TrainingClasses> update(TrainingClasses service)
    {
        return sseService.publishEnt( service.getRegion(),
                new SseEvent(SseType.SSE_ENT, service.getBusinessid(), "trainingclasses", "", "", "") )
                .flatMap( bb ->{
                    return serviceRepository.save(service);
                });
        //    return serviceRepository.save(service);
    }

    public Mono<Void> deleteById(final String id){
        return serviceRepository.deleteById(id);
    }

    public Mono<TrainingClasses> findById(final String id){
        return serviceRepository.findById(id);
    }
    public Flux<TrainingClasses> findByBusinessid(final String id) {
        return serviceRepository.findByBusinessid(id);
    }
    public Flux<TrainingClasses> findByBusinessidInc(final String id,final Date date) {
        return serviceRepository.findByBusinessidInc(id,date);
    }
    public Flux<TrainingClasses> findDynamics(final String id) {
        return serviceRepository.findDynamics(id);
    }
    public Flux<TrainingClasses> findDynamicsInc(final String id, final Date date) {
        return serviceRepository.findDynamicsInc(id,date);
    }

    public Flux<TrainingClasses> findByRegion(final String id) {
        return serviceRepository.findByRegion(id);
    }
    public Flux<TrainingClasses> findByRegionInc(final String id, final Date date) {
        return serviceRepository.findByRegionInc(id,date);
    }
    public Mono<Long> countActive(final String id){
        return serviceRepository.countActive(id);
    }
}