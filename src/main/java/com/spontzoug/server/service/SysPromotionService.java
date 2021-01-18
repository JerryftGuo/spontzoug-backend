package com.spontzoug.server.service;

import com.spontzoug.server.model.SysPromotion;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.ISysPromotionRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class SysPromotionService implements ISysPromotionService {
    @Autowired
    private ISysPromotionRepository syspromotionRepository;
    @Autowired
    ISseService sseService;

    public void create(SysPromotion syspromotion)
    {
        /*
        syspromotionRepository.save(syspromotion)
                .flatMap( biz -> {
                            return sseService.publishEnt( syspromotion.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, biz.getBusinessid(), "syspromotion", "", "", ""));
                        }
                ).subscribe();

         */
        syspromotionRepository.save(syspromotion).subscribe();
    }

    public Mono<SysPromotion> update(SysPromotion syspromotion)
    {
        /*
        return sseService.publishEnt( syspromotion.getRegion(),
                new SseEvent(SseType.SSE_ENT, syspromotion.getBusinessid(), "syspromotion", "", "", "") )
                .flatMap( bb ->{
                    return syspromotionRepository.save(syspromotion);
                });

         */
        return syspromotionRepository.save(syspromotion);

    }

    public Mono<Void> deleteById(final String id){
        return syspromotionRepository.deleteById(id);
    }

    public Mono<SysPromotion> findById(final String id){
        return syspromotionRepository.findById(id);
    }
    public Flux<SysPromotion> findByBusinessid(final String id) {
        return syspromotionRepository.findByBusinessid(id);
    }
    public Flux<SysPromotion> findByBusinessidInc(final String id, final Date date) {
        return syspromotionRepository.findByBusinessidInc(id,date);
    }
    public Flux<SysPromotion> findDynamics(final String id) {
        return syspromotionRepository.findDynamics(id);
    }
    public Flux<SysPromotion> findDynamicsInc(final String id,final Date date) {
        return syspromotionRepository.findDynamicsInc(id,date);
    }
}