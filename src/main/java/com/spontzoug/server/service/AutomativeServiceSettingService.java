package com.spontzoug.server.service;

import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.AutomativeServiceSetting;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IAutomativeServiceSettingRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class AutomativeServiceSettingService implements IAutomativeServiceSettingService {
    @Autowired
    private IAutomativeServiceSettingRepository settingRepository;
    @Autowired
    ISseService sseService;

    public void create(AutomativeServiceSetting setting)
    {
        settingRepository.save(setting)
                .flatMap( biz -> {
                            return sseService.publishEnt( setting.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, biz.getBusinessid(), "automativeservicesetting", "", "", ""));
                        }
                ).subscribe();
    }

    public Mono<AutomativeServiceSetting> update(AutomativeServiceSetting setting)
    {
        return sseService.publishEnt( setting.getRegion(),
                new SseEvent(SseType.SSE_ENT, setting.getBusinessid(), "automativeservicesetting", "", "", "") )
                .flatMap( bb ->{
                    return settingRepository.save(setting);
                });
        // return settingRepository.save(setting);

    }

    public Mono<Void> deleteById(final String id){
        return settingRepository.deleteById(id);
    }

    public Mono<AutomativeServiceSetting> findById(final String id){
        return settingRepository.findById(id);
    }
    public Flux<AutomativeServiceSetting> findByBusinessid(final String id) {
        return settingRepository.findByBusinessid(id);
    }
    public Flux<AutomativeServiceSetting> findByBusinessidInc(final String id, final Date date) {
        return settingRepository.findByBusinessidInc(id,date);
    }
    public Flux<AutomativeServiceSetting> findDynamics(final String id) {
        return settingRepository.findDynamics(id);
    }
    public Flux<AutomativeServiceSetting> findDynamicsInc(final String id,final Date date) {
        return settingRepository.findDynamicsInc(id,date);
    }
    public Flux<AutomativeServiceSetting> findByRegion(final String region) {
        return settingRepository.findByRegion(region);
    }

    public Flux<AutomativeServiceSetting> findByRegionInc(final String region, final Date date) {
        return settingRepository.findByRegionInc(region,date);
    }
    public Flux<AutomativeServiceSetting> findAllInc(final Date date) {
        return settingRepository.findAllInc(date);
    }
    public Flux<AutomativeServiceSetting> findAll() {
        return settingRepository.findAll();
    }

}