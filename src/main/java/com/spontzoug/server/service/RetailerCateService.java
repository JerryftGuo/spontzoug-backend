package com.spontzoug.server.service;

import com.spontzoug.server.model.RetailerCate;
import com.spontzoug.server.model.RetailerMenu;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IRetailerCateRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class RetailerCateService implements IRetailerCateService {
    @Autowired
    private IRetailerCateRepository retailerCateRepository;
    @Autowired
    private ISseService sseService;

    public void create(RetailerCate retailerCate)
    {
        retailerCateRepository.save(retailerCate)
                .flatMap( mn -> {
                            return sseService.publishEnt( mn.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, mn.getBusinessid(), "retailercate", "", "", ""));
                        }
                ).subscribe();
        //this.retailerCateRepository.save(RetailerCate).subscribe();
    }

    public Mono<RetailerCate> update(RetailerCate retailerCate)
    {
        return sseService.publishEnt( retailerCate.getRegion(),
                new SseEvent(SseType.SSE_ENT, retailerCate.getBusinessid(), "retailercate", "", "", "") )
                .flatMap( bb ->{
                    return retailerCateRepository.save(retailerCate);
                });
        //return this.retailerCateRepository.save(retailerCate);

    }

    public Mono<Void> deleteById(final String id){
        return this.retailerCateRepository.deleteById(id);
    }

    public Mono<RetailerCate> findById(final String id){
        return this.retailerCateRepository.findById(id);
    }
    public Flux<RetailerCate> findByBusinessid(final String id) {
        return this.retailerCateRepository.findByBusinessid(id);
    }
    public Flux<RetailerCate> findByBusinessidInc(final String id,final Date date) {
        return this.retailerCateRepository.findByBusinessidInc(id,date);
    }
    public Flux<RetailerCate> findDynamics(final String id) {
        return this.retailerCateRepository.findDynamics(id);
    }
    public Flux<RetailerCate> findDynamicsInc(final String id,final Date date) {
        return this.retailerCateRepository.findDynamicsInc(id,date);
    }

    public Flux<RetailerCate> findByRegion(final String region) {
        return retailerCateRepository.findByRegion(region);
    }

    public Flux<RetailerCate> findByRegionInc(final String region, final Date date) {
        return retailerCateRepository.findByRegionInc(region,date);
    }
}