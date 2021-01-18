package com.spontzoug.server.service;

import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.AutomotiveSaleSetting;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IAutomotiveSaleSettingRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class AutomotiveSaleSettingService implements IAutomotiveSaleSettingService {
    @Autowired
    private IAutomotiveSaleSettingRepository settingRepository;
    @Autowired
    ISseService sseService;

    public void create(AutomotiveSaleSetting setting)
    {
        settingRepository.save(setting)
                .flatMap( biz -> {
                            return sseService.publishEnt( setting.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, biz.getBusinessid(), "automotivesalesetting", "", "", ""));
                        }
                ).subscribe();
    }

    public Mono<AutomotiveSaleSetting> update(AutomotiveSaleSetting setting)
    {
        return sseService.publishEnt( setting.getRegion(),
                new SseEvent(SseType.SSE_ENT, setting.getBusinessid(), "automotivesalesetting", "", "", "") )
                .flatMap( bb ->{
                    return settingRepository.save(setting);
                });
        // return settingRepository.save(setting);

    }

    public Mono<Void> deleteById(final String id){
        return settingRepository.deleteById(id);
    }

    public Mono<AutomotiveSaleSetting> findById(final String id){
        return settingRepository.findById(id);
    }
    public Flux<AutomotiveSaleSetting> findByBusinessid(final String id) {
        return settingRepository.findByBusinessid(id);
    }
    public Flux<AutomotiveSaleSetting> findByBusinessidInc(final String id, final Date date) {
        return settingRepository.findByBusinessidInc(id,date);
    }
    public Flux<AutomotiveSaleSetting> findDynamics(final String id) {
        return settingRepository.findDynamics(id);
    }
    public Flux<AutomotiveSaleSetting> findDynamicsInc(final String id,final Date date) {
        return settingRepository.findDynamicsInc(id,date);
    }
    public Flux<AutomotiveSaleSetting> findByRegion(final String region) {
        return settingRepository.findByRegion(region);
    }

    public Flux<AutomotiveSaleSetting> findByRegionInc(final String region, final Date date) {
        return settingRepository.findByRegionInc(region,date);
    }
    public Flux<AutomotiveSaleSetting> findAllInc(final Date date) {
        return settingRepository.findAllInc(date);
    }
    public Flux<AutomotiveSaleSetting> findAll() {
        return settingRepository.findAll();
    }

}