package com.spontzoug.server.service;

import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.AutomativeSaleSetting;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IAutomativeSaleSettingRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class AutomativeSaleSettingService implements IAutomativeSaleSettingService {
    @Autowired
    private IAutomativeSaleSettingRepository settingRepository;
    @Autowired
    ISseService sseService;

    public void create(AutomativeSaleSetting setting)
    {
        settingRepository.save(setting)
                .flatMap( biz -> {
                            return sseService.publishEnt( setting.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, biz.getBusinessid(), "automativesalesetting", "", "", ""));
                        }
                ).subscribe();
    }

    public Mono<AutomativeSaleSetting> update(AutomativeSaleSetting setting)
    {
        return sseService.publishEnt( setting.getRegion(),
                new SseEvent(SseType.SSE_ENT, setting.getBusinessid(), "automativesalesetting", "", "", "") )
                .flatMap( bb ->{
                    return settingRepository.save(setting);
                });
        // return settingRepository.save(setting);

    }

    public Mono<Void> deleteById(final String id){
        return settingRepository.deleteById(id);
    }

    public Mono<AutomativeSaleSetting> findById(final String id){
        return settingRepository.findById(id);
    }
    public Flux<AutomativeSaleSetting> findByBusinessid(final String id) {
        return settingRepository.findByBusinessid(id);
    }
    public Flux<AutomativeSaleSetting> findByBusinessidInc(final String id, final Date date) {
        return settingRepository.findByBusinessidInc(id,date);
    }
    public Flux<AutomativeSaleSetting> findDynamics(final String id) {
        return settingRepository.findDynamics(id);
    }
    public Flux<AutomativeSaleSetting> findDynamicsInc(final String id,final Date date) {
        return settingRepository.findDynamicsInc(id,date);
    }
    public Flux<AutomativeSaleSetting> findByRegion(final String region) {
        return settingRepository.findByRegion(region);
    }

    public Flux<AutomativeSaleSetting> findByRegionInc(final String region, final Date date) {
        return settingRepository.findByRegionInc(region,date);
    }
    public Flux<AutomativeSaleSetting> findAllInc(final Date date) {
        return settingRepository.findAllInc(date);
    }
    public Flux<AutomativeSaleSetting> findAll() {
        return settingRepository.findAll();
    }

}