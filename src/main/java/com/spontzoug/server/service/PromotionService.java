package com.spontzoug.server.service;

import com.spontzoug.server.model.Promotion;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IPromotionRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class PromotionService implements IPromotionService {
    @Autowired
    private IPromotionRepository promotionRepository;
    @Autowired
    private ISseService sseService;

    public void create(Promotion promotion)
    {
        promotionRepository.save(promotion)
            .flatMap( pro -> {
                        return sseService.publishEnt( pro.getRegion(),
                                new SseEvent(SseType.SSE_ENT, pro.getBusinessid(), "promotion", "", "", ""));
                    }
            ).subscribe();
        //promotionRepository.save(promotion).subscribe();
    }

    public Mono<Promotion> update(Promotion promotion){
        return sseService.publishEnt( promotion.getRegion(),
                new SseEvent(SseType.SSE_ENT, promotion.getBusinessid(), "promotion", "", "", "") )
                .flatMap( bb ->{
                    return promotionRepository.save(promotion);
                });
       // return promotionRepository.save(promotion);
    }

    public Mono<Void> deleteById(final String id){
        return promotionRepository.deleteById(id);
    }

    public Mono<Promotion> findById(final String id){
        return promotionRepository.findById(id);
    }
    public Flux<Promotion> findByBusinessid(final String id) {
        return promotionRepository.findByBusinessid(id);
    }
    public Flux<Promotion> findByBusinessidInc(final String id, final Date date) {
        return promotionRepository.findByBusinessidInc(id,date);
    }
    public Flux<Promotion> findDynamics(final String id) {
        return promotionRepository.findDynamics(id);
    }
    public Flux<Promotion> findDynamicsInc(final String id, final Date date) {
        return promotionRepository.findDynamicsInc(id,date);
    }
}