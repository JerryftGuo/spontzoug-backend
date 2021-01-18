package com.spontzoug.server.service;

import com.spontzoug.server.model.PlanOption;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IPlanOptionRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class PlanOptionService implements IPlanOptionService {
    @Autowired
    private IPlanOptionRepository planoptionRepository;
    @Autowired
    ISseService sseService;

    public void create(PlanOption planoption)
    {
        planoptionRepository.save(planoption).subscribe();
        /*
                .flatMap( biz -> {
                            return sseService.publishEnt( planoption.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, biz.getBusinessid(), "planoption", "", "", ""));
                        }
                ).subscribe();

         */
    }

    public Mono<PlanOption> update(PlanOption planoption)
    {
        /*
        return sseService.publishEnt( planoption.getRegion(),
                new SseEvent(SseType.SSE_ENT, planoption.getBusinessid(), "planoption", "", "", "") )
                .flatMap( bb ->{
                    return planoptionRepository.save(planoption);
                });

         */
         return planoptionRepository.save(planoption);

    }

    public Mono<Void> deleteById(final String id){
        return planoptionRepository.deleteById(id);
    }

    public Mono<PlanOption> findById(final String id){
        return planoptionRepository.findById(id);
    }
    public Flux<PlanOption> findByBusinessid(final String id) {
        return planoptionRepository.findByBusinessid(id);
    }
    public Flux<PlanOption> findByBusinessidInc(final String id, final Date date) {
        return planoptionRepository.findByBusinessidInc(id,date);
    }
    public Flux<PlanOption> findDynamics(final String id) {
        return planoptionRepository.findDynamics(id);
    }
    public Flux<PlanOption> findDynamicsInc(final String id,final Date date) {
        return planoptionRepository.findDynamicsInc(id,date);
    }
}