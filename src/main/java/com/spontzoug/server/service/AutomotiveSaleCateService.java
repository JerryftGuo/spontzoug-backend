package com.spontzoug.server.service;

import com.spontzoug.server.model.AutomotiveSaleCate;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IAutomotiveSaleCateRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class AutomotiveSaleCateService implements IAutomotiveSaleCateService {
    @Autowired
    private IAutomotiveSaleCateRepository retailerCateRepository;
    @Autowired
    private ISseService sseService;

    public void create(AutomotiveSaleCate retailerCate)
    {
        retailerCateRepository.save(retailerCate)
                .flatMap( mn -> {
                            return sseService.publishEnt( mn.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, mn.getBusinessid(), "automotivesalecate", "", "", ""));
                        }
                ).subscribe();
        //this.retailerCateRepository.save(AutomotiveSaleCate).subscribe();
    }

    public Mono<AutomotiveSaleCate> update(AutomotiveSaleCate retailerCate)
    {
        return sseService.publishEnt( retailerCate.getRegion(),
                new SseEvent(SseType.SSE_ENT, retailerCate.getBusinessid(), "automotivesalecate", "", "", "") )
                .flatMap( bb ->{
                    return retailerCateRepository.save(retailerCate);
                });
        //return this.retailerCateRepository.save(retailerCate);

    }

    public Mono<Void> deleteById(final String id){
        return this.retailerCateRepository.deleteById(id);
    }

    public Mono<AutomotiveSaleCate> findById(final String id){
        return this.retailerCateRepository.findById(id);
    }
    public Flux<AutomotiveSaleCate> findByBusinessid(final String id) {
        return this.retailerCateRepository.findByBusinessid(id);
    }
    public Flux<AutomotiveSaleCate> findByBusinessidInc(final String id,final Date date) {
        return this.retailerCateRepository.findByBusinessidInc(id,date);
    }
    public Flux<AutomotiveSaleCate> findDynamics(final String id) {
        return this.retailerCateRepository.findDynamics(id);
    }
    public Flux<AutomotiveSaleCate> findDynamicsInc(final String id,final Date date) {
        return this.retailerCateRepository.findDynamicsInc(id,date);
    }

    public Flux<AutomotiveSaleCate> findByRegion(final String region) {
        return retailerCateRepository.findByRegion(region);
    }

    public Flux<AutomotiveSaleCate> findByRegionInc(final String region, final Date date) {
        return retailerCateRepository.findByRegionInc(region,date);
    }
}