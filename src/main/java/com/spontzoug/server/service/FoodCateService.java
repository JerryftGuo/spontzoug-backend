package com.spontzoug.server.service;

import com.spontzoug.server.model.FoodCate;
import com.spontzoug.server.model.FoodMenu;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IFoodCateRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class FoodCateService implements IFoodCateService {
    @Autowired
    private IFoodCateRepository foodCateRepository;
    @Autowired
    private ISseService sseService;

    public void create(FoodCate foodCate)
    {
        foodCateRepository.save(foodCate)
                .flatMap( mn -> {
                            return sseService.publishEnt( mn.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, mn.getBusinessid(), "menu_cat", "", "", ""));
                        }
                ).subscribe();
        //this.foodCateRepository.save(FoodCate).subscribe();
    }

    public Mono<FoodCate> update(FoodCate foodCate)
    {
        return sseService.publishEnt( foodCate.getRegion(),
                new SseEvent(SseType.SSE_ENT, foodCate.getBusinessid(), "menu_cat", "", "", "") )
                .flatMap( bb ->{
                    return foodCateRepository.save(foodCate);
                });
        //return this.foodCateRepository.save(foodCate);

    }

    public Mono<Void> deleteById(final String id){
        return this.foodCateRepository.deleteById(id);
    }

    public Mono<FoodCate> findById(final String id){
        return this.foodCateRepository.findById(id);
    }
    public Flux<FoodCate> findByBusinessid(final String id) {
        return this.foodCateRepository.findByBusinessid(id);
    }
    public Flux<FoodCate> findByBusinessidInc(final String id,final Date date) {
        return this.foodCateRepository.findByBusinessidInc(id,date);
    }
    public Flux<FoodCate> findDynamics(final String id) {
        return this.foodCateRepository.findDynamics(id);
    }
    public Flux<FoodCate> findDynamicsInc(final String id,final Date date) {
        return this.foodCateRepository.findDynamicsInc(id,date);
    }

    public Flux<FoodCate> findByRegion(final String region) {
        return foodCateRepository.findByRegion(region);
    }

    public Flux<FoodCate> findByRegionInc(final String region, final Date date) {
        return foodCateRepository.findByRegionInc(region,date);
    }
}