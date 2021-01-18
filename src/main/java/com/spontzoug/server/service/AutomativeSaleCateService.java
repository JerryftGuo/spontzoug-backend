package com.spontzoug.server.service;

import com.spontzoug.server.model.AutomativeSaleCate;
import com.spontzoug.server.model.AutomativeSaleMenu;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IAutomativeSaleCateRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class AutomativeSaleCateService implements IAutomativeSaleCateService {
    @Autowired
    private IAutomativeSaleCateRepository retailerCateRepository;
    @Autowired
    private ISseService sseService;

    public void create(AutomativeSaleCate retailerCate)
    {
        retailerCateRepository.save(retailerCate)
                .flatMap( mn -> {
                            return sseService.publishEnt( mn.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, mn.getBusinessid(), "automativesalecate", "", "", ""));
                        }
                ).subscribe();
        //this.retailerCateRepository.save(AutomativeSaleCate).subscribe();
    }

    public Mono<AutomativeSaleCate> update(AutomativeSaleCate retailerCate)
    {
        return sseService.publishEnt( retailerCate.getRegion(),
                new SseEvent(SseType.SSE_ENT, retailerCate.getBusinessid(), "automativesalecate", "", "", "") )
                .flatMap( bb ->{
                    return retailerCateRepository.save(retailerCate);
                });
        //return this.retailerCateRepository.save(retailerCate);

    }

    public Mono<Void> deleteById(final String id){
        return this.retailerCateRepository.deleteById(id);
    }

    public Mono<AutomativeSaleCate> findById(final String id){
        return this.retailerCateRepository.findById(id);
    }
    public Flux<AutomativeSaleCate> findByBusinessid(final String id) {
        return this.retailerCateRepository.findByBusinessid(id);
    }
    public Flux<AutomativeSaleCate> findByBusinessidInc(final String id,final Date date) {
        return this.retailerCateRepository.findByBusinessidInc(id,date);
    }
    public Flux<AutomativeSaleCate> findDynamics(final String id) {
        return this.retailerCateRepository.findDynamics(id);
    }
    public Flux<AutomativeSaleCate> findDynamicsInc(final String id,final Date date) {
        return this.retailerCateRepository.findDynamicsInc(id,date);
    }

    public Flux<AutomativeSaleCate> findByRegion(final String region) {
        return retailerCateRepository.findByRegion(region);
    }

    public Flux<AutomativeSaleCate> findByRegionInc(final String region, final Date date) {
        return retailerCateRepository.findByRegionInc(region,date);
    }
}