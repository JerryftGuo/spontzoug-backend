package com.spontzoug.server.service;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.FoodMenu;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IFoodMenuRepository;
import com.spontzoug.server.util.SseChannel;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class FoodMenuService implements IFoodMenuService {
    @Autowired
    private IFoodMenuRepository foodMenuRepository;
    @Autowired
    private ISseService sseService;

    public void create(FoodMenu foodMenu) {
        foodMenuRepository.save(foodMenu)
                .flatMap( mn -> {
                            return sseService.publishEnt( mn.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, mn.getBusinessid(), "foodMenu", "", "", ""));
                        }
                ).subscribe();
    }

    public Mono<FoodMenu> update(FoodMenu foodMenu)
    {
        return sseService.publishEnt( foodMenu.getRegion(),
                new SseEvent(SseType.SSE_ENT, foodMenu.getBusinessid(), "foodMenu", "", "", "") )
                .flatMap( bb ->{
                    return foodMenuRepository.save(foodMenu);
                });
        //  return foodMenuRepository.save(foodMenu);

    }

    public Mono<Void> deleteById(final String id){
        return foodMenuRepository.deleteById(id);
    }

    public Mono<FoodMenu> findById(final String id){
        return foodMenuRepository.findById(id);
    }
    public Flux<FoodMenu> findByBusinessid(final String id) {
        return foodMenuRepository.findByBusinessid(id);
    }
    public Flux<FoodMenu> findByBusinessidInc(final String id,final Date date) {
        return foodMenuRepository.findByBusinessidInc(id,date);
    }
    public Flux<FoodMenu> findDynamics(final String id) {
        return foodMenuRepository.findDynamics(id);
    }
    public Flux<FoodMenu> findDynamicsInc(final String id, final Date date) {
        return foodMenuRepository.findDynamicsInc(id,date);
    }

    public Flux<FoodMenu> findByRegion(final String region) {
        return foodMenuRepository.findByRegion(region);
    }

    public Flux<FoodMenu> findByRegionInc(final String region, final Date date) {
        return foodMenuRepository.findByRegionInc(region,date);
    }

    public Mono<Long> countActive(final String id){
        return foodMenuRepository.countActive(id);
    }
    public Mono<Long> countDeleting(final String id, final Date date1, final Date date2){
        return foodMenuRepository.countDeleting(id, date1, date2);
    }
}