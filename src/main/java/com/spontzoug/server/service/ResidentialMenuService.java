package com.spontzoug.server.service;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.ResidentialMenu;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IResidentialMenuRepository;
import com.spontzoug.server.util.SseChannel;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class ResidentialMenuService implements IResidentialMenuService {
    @Autowired
    private IResidentialMenuRepository retailerMenuRepository;
    @Autowired
    private ISseService sseService;

    public void create(ResidentialMenu retailerMenu) {
        retailerMenuRepository.save(retailerMenu)
                .flatMap( mn -> {
                            return sseService.publishEnt( mn.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, mn.getBusinessid(), "residentialmenu", "", "", ""));
                        }
                ).subscribe();
    }

    public Mono<ResidentialMenu> update(ResidentialMenu retailerMenu)
    {
        return sseService.publishEnt( retailerMenu.getRegion(),
                new SseEvent(SseType.SSE_ENT, retailerMenu.getBusinessid(), "residentialmenu", "", "", "") )
                .flatMap( bb ->{
                    return retailerMenuRepository.save(retailerMenu);
                });
        //  return retailerMenuRepository.save(retailerMenu);

    }

    public Mono<Void> deleteById(final String id){
        return retailerMenuRepository.deleteById(id);
    }

    public Mono<ResidentialMenu> findById(final String id){
        return retailerMenuRepository.findById(id);
    }
    public Flux<ResidentialMenu> findByBusinessid(final String id) {
        return retailerMenuRepository.findByBusinessid(id);
    }
    public Flux<ResidentialMenu> findByBusinessidInc(final String id,final Date date) {
        return retailerMenuRepository.findByBusinessidInc(id,date);
    }
    public Flux<ResidentialMenu> findDynamics(final String id) {
        return retailerMenuRepository.findDynamics(id);
    }
    public Flux<ResidentialMenu> findDynamicsInc(final String id, final Date date) {
        return retailerMenuRepository.findDynamicsInc(id,date);
    }

    public Flux<ResidentialMenu> findByRegion(final String region) {
        return retailerMenuRepository.findByRegion(region);
    }

    public Flux<ResidentialMenu> findByRegionInc(final String region, final Date date) {
        return retailerMenuRepository.findByRegionInc(region,date);
    }


    public Mono<Long> countActive(final String id){
        return retailerMenuRepository.countActive(id);
    }
    public Mono<Long> countDeleting(final String id, final Date date1, final Date date2){
        return retailerMenuRepository.countDeleting(id, date1, date2);
    }
}