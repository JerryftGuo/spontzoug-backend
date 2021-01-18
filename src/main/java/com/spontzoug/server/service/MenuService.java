package com.spontzoug.server.service;

import com.spontzoug.server.model.Menu;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IMenuRepository;
import com.spontzoug.server.util.SseChannel;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class MenuService implements IMenuService {
    @Autowired
    private IMenuRepository menuRepository;
    @Autowired
    private ISseService sseService;

    public void create(Menu menu) {
        menuRepository.save(menu)
                .flatMap( mn -> {
                            return sseService.publishEnt( mn.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, mn.getBusinessid(), "menu", "", "", ""));
                        }
                ).subscribe();
    }

    public Mono<Menu> update(Menu menu)
    {
        return sseService.publishEnt( menu.getRegion(),
                new SseEvent(SseType.SSE_ENT, menu.getBusinessid(), "menu", "", "", "") )
                .flatMap( bb ->{
                    return menuRepository.save(menu);
                });
      //  return menuRepository.save(menu);

    }

    public Mono<Void> deleteById(final String id){
        return menuRepository.deleteById(id);
    }

    public Mono<Menu> findById(final String id){
        return menuRepository.findById(id);
    }
    public Flux<Menu> findByBusinessid(final String id) {
        return menuRepository.findByBusinessid(id);
    }
    public Flux<Menu> findByBusinessidInc(final String id,final Date date) {
        return menuRepository.findByBusinessidInc(id,date);
    }
    public Flux<Menu> findDynamics(final String id) {
        return menuRepository.findDynamics(id);
    }
    public Flux<Menu> findDynamicsInc(final String id, final Date date) {
        return menuRepository.findDynamicsInc(id,date);
    }
}