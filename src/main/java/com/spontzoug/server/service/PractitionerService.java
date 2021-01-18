package com.spontzoug.server.service;

import com.spontzoug.server.model.Practitioner;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IPractitionerRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class PractitionerService implements IPractitionerService {
    @Autowired
    private IPractitionerRepository practitionerRepository;
    @Autowired
    private ISseService sseService;

    public void create(Practitioner practitioner)
    {
        practitionerRepository.save(practitioner)
                .flatMap( pra -> {
                            return sseService.publishEnt( pra.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, pra.getBusinessid(), "practitioner", "", "", ""));
                        }
                ).subscribe();
    //    practitionerRepository.save(practitioner).subscribe();

    }
    public Mono<Practitioner> update(Practitioner practitioner)
    {
        return sseService.publishEnt( practitioner.getRegion(),
                new SseEvent(SseType.SSE_ENT, practitioner.getBusinessid(), "practitioner", "", "", "") )
                .flatMap( bb ->{
                    return practitionerRepository.save(practitioner);
                });
    //    return practitionerRepository.save(practitioner);

    }
    public Mono<Void> deleteById(final String id){
        return practitionerRepository.deleteById(id);
    }
    public Mono<Practitioner> findById(final String id){
        return practitionerRepository.findById(id);
    }
    public Flux<Practitioner> findByBusinessid(final String id){
        return practitionerRepository.findByBusinessid(id);
    }
    public Flux<Practitioner> findByBusinessidInc(final String id, final Date date){
        return practitionerRepository.findByBusinessidInc(id,date);
    }
    public Flux<Practitioner> findDynamics(final String id){
        return practitionerRepository.findDynamics(id);
    }
    public Flux<Practitioner> findDynamicsInc(final String id, final Date date){
        return practitionerRepository.findDynamicsInc(id,date);
    }
}