package com.spontzoug.server.service;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.RealEstateMenu;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IRealEstateMenuRepository;
import com.spontzoug.server.util.SseChannel;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class RealEstateMenuService implements IRealEstateMenuService {
    @Autowired
    private IRealEstateMenuRepository realestateMenuRepository;
    @Autowired
    private ISseService sseService;

    public void create(RealEstateMenu realestateMenu) {
        realestateMenuRepository.save(realestateMenu)
                .flatMap( mn -> {
                            return sseService.publishEnt( mn.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, mn.getBusinessid(), "realestatemenu", "", "", ""));
                        }
                ).subscribe();
    }

    public Mono<RealEstateMenu> update(RealEstateMenu realestateMenu)
    {
        return sseService.publishEnt( realestateMenu.getRegion(),
                new SseEvent(SseType.SSE_ENT, realestateMenu.getBusinessid(), "realestatemenu", "", "", "") )
                .flatMap( bb ->{
                    return realestateMenuRepository.save(realestateMenu);
                });
        //  return realestateMenuRepository.save(realestateMenu);

    }

    public Mono<Void> deleteById(final String id){
        return realestateMenuRepository.deleteById(id);
    }

    public Mono<RealEstateMenu> findById(final String id){
        return realestateMenuRepository.findById(id);
    }
    public Flux<RealEstateMenu> findByBusinessid(final String id) {
        return realestateMenuRepository.findByBusinessid(id);
    }
    public Flux<RealEstateMenu> findByBusinessidInc(final String id,final Date date) {
        return realestateMenuRepository.findByBusinessidInc(id,date);
    }
    public Flux<RealEstateMenu> findDynamics(final String id) {
        return realestateMenuRepository.findDynamics(id);
    }
    public Flux<RealEstateMenu> findDynamicsInc(final String id, final Date date) {
        return realestateMenuRepository.findDynamicsInc(id,date);
    }

    public Flux<RealEstateMenu> findByRegion(final String region) {
        return realestateMenuRepository.findByRegion(region);
    }

    public Flux<RealEstateMenu> findByRegionInc(final String region, final Date date) {
        return realestateMenuRepository.findByRegionInc(region,date);
    }


    public Mono<Long> countActive(final String id){
        return realestateMenuRepository.countActive(id);
    }
    public Mono<Long> countDeleting(final String id, final Date date1, final Date date2){
        return realestateMenuRepository.countDeleting(id, date1, date2);
    }
}