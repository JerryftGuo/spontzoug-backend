package com.spontzoug.server.service;

import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.HealthSetting;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IHealthSettingRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class HealthSettingService implements IHealthSettingService {
    @Autowired
    private IHealthSettingRepository settingRepository;
    @Autowired
    ISseService sseService;

    public void create(HealthSetting setting)
    {
        settingRepository.save(setting)
                .flatMap( biz -> {
                            return sseService.publishEnt( setting.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, biz.getBusinessid(), "setting", "", "", ""));
                        }
                ).subscribe();
    }

    public Mono<HealthSetting> update(HealthSetting setting)
    {
        return sseService.publishEnt( setting.getRegion(),
                new SseEvent(SseType.SSE_ENT, setting.getBusinessid(), "setting", "", "", "") )
                .flatMap( bb ->{
                    return settingRepository.save(setting);
                });
        // return settingRepository.save(setting);

    }

    public Mono<Void> deleteById(final String id){
        return settingRepository.deleteById(id);
    }

    public Mono<HealthSetting> findById(final String id){
        return settingRepository.findById(id);
    }
    public Flux<HealthSetting> findByBusinessid(final String id) {
        return settingRepository.findByBusinessid(id);
    }
    public Flux<HealthSetting> findByBusinessidInc(final String id, final Date date) {
        return settingRepository.findByBusinessidInc(id,date);
    }
    public Flux<HealthSetting> findDynamics(final String id) {
        return settingRepository.findDynamics(id);
    }
    public Flux<HealthSetting> findDynamicsInc(final String id,final Date date) {
        return settingRepository.findDynamicsInc(id,date);
    }
    public Flux<HealthSetting> findByRegion(final String region) {
        return settingRepository.findByRegion(region);
    }

    public Flux<HealthSetting> findByRegionInc(final String region, final Date date) {
        return settingRepository.findByRegionInc(region,date);
    }
    public Flux<HealthSetting> findAllInc(final Date date) {
        return settingRepository.findAllInc(date);
    }
    public Flux<HealthSetting> findAll() {
        return settingRepository.findAll();
    }

}