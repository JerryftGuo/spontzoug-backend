package com.spontzoug.server.service;

import com.spontzoug.server.model.ResidentialCate;
import com.spontzoug.server.model.ResidentialMenu;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IResidentialCateRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class ResidentialCateService implements IResidentialCateService {
    @Autowired
    private IResidentialCateRepository retailerCateRepository;
    @Autowired
    private ISseService sseService;

    public void create(ResidentialCate retailerCate)
    {
        retailerCateRepository.save(retailerCate)
                .flatMap( mn -> {
                            return sseService.publishEnt( mn.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, mn.getBusinessid(), "residentialcate", "", "", ""));
                        }
                ).subscribe();
        //this.retailerCateRepository.save(ResidentialCate).subscribe();
    }

    public Mono<ResidentialCate> update(ResidentialCate retailerCate)
    {
        return sseService.publishEnt( retailerCate.getRegion(),
                new SseEvent(SseType.SSE_ENT, retailerCate.getBusinessid(), "residentialcate", "", "", "") )
                .flatMap( bb ->{
                    return retailerCateRepository.save(retailerCate);
                });
        //return this.retailerCateRepository.save(retailerCate);

    }

    public Mono<Void> deleteById(final String id){
        return this.retailerCateRepository.deleteById(id);
    }

    public Mono<ResidentialCate> findById(final String id){
        return this.retailerCateRepository.findById(id);
    }
    public Flux<ResidentialCate> findByBusinessid(final String id) {
        return this.retailerCateRepository.findByBusinessid(id);
    }
    public Flux<ResidentialCate> findByBusinessidInc(final String id,final Date date) {
        return this.retailerCateRepository.findByBusinessidInc(id,date);
    }
    public Flux<ResidentialCate> findDynamics(final String id) {
        return this.retailerCateRepository.findDynamics(id);
    }
    public Flux<ResidentialCate> findDynamicsInc(final String id,final Date date) {
        return this.retailerCateRepository.findDynamicsInc(id,date);
    }

    public Flux<ResidentialCate> findByRegion(final String region) {
        return retailerCateRepository.findByRegion(region);
    }

    public Flux<ResidentialCate> findByRegionInc(final String region, final Date date) {
        return retailerCateRepository.findByRegionInc(region,date);
    }
}