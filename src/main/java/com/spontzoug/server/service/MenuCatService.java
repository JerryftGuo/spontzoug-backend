package com.spontzoug.server.service;

import com.spontzoug.server.model.MenuCat;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IMenuCatRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class MenuCatService implements IMenuCatService {
    @Autowired
    private IMenuCatRepository menuCatRepository;
    @Autowired
    private ISseService sseService;

    public void create(MenuCat menuCat)
    {
        menuCatRepository.save(menuCat)
                .flatMap( mn -> {
                            return sseService.publishEnt( mn.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, mn.getBusinessid(), "menu_cat", "", "", ""));
                        }
                ).subscribe();
        //this.menuCatRepository.save(MenuCat).subscribe();
    }

    public Mono<MenuCat> update(MenuCat menuCat)
    {
        return sseService.publishEnt( menuCat.getRegion(),
                new SseEvent(SseType.SSE_ENT, menuCat.getBusinessid(), "menu_cat", "", "", "") )
                .flatMap( bb ->{
                    return menuCatRepository.save(menuCat);
                });
        //return this.menuCatRepository.save(menuCat);

    }

    public Mono<Void> deleteById(final String id){
        return this.menuCatRepository.deleteById(id);
    }

    public Mono<MenuCat> findById(final String id){
        return this.menuCatRepository.findById(id);
    }
    public Flux<MenuCat> findByBusinessid(final String id) {
        return this.menuCatRepository.findByBusinessid(id);
    }
    public Flux<MenuCat> findByBusinessidInc(final String id,final Date date) {
        return this.menuCatRepository.findByBusinessidInc(id,date);
    }
    public Flux<MenuCat> findDynamics(final String id) {
        return this.menuCatRepository.findDynamics(id);
    }
    public Flux<MenuCat> findDynamicsInc(final String id,final Date date) {
        return this.menuCatRepository.findDynamicsInc(id,date);
    }
}