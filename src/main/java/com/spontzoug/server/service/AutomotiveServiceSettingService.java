package com.spontzoug.server.service;

import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.AutomotiveServiceSetting;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IAutomotiveServiceSettingRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class AutomotiveServiceSettingService implements IAutomotiveServiceSettingService {
    @Autowired
    private IAutomotiveServiceSettingRepository settingRepository;
    @Autowired
    ISseService sseService;

    public void create(AutomotiveServiceSetting setting)
    {
        settingRepository.save(setting)
                .flatMap( biz -> {
                            return sseService.publishEnt( setting.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, biz.getBusinessid(), "automotiveservicesetting", "", "", ""));
                        }
                ).subscribe();
    }

    public Mono<AutomotiveServiceSetting> update(AutomotiveServiceSetting setting)
    {
        return sseService.publishEnt( setting.getRegion(),
                new SseEvent(SseType.SSE_ENT, setting.getBusinessid(), "automotiveservicesetting", "", "", "") )
                .flatMap( bb ->{
                    return settingRepository.save(setting);
                });
        // return settingRepository.save(setting);

    }

    public Mono<Void> deleteById(final String id){
        return settingRepository.deleteById(id);
    }

    public Mono<AutomotiveServiceSetting> findById(final String id){
        return settingRepository.findById(id);
    }
    public Flux<AutomotiveServiceSetting> findByBusinessid(final String id) {
        return settingRepository.findByBusinessid(id);
    }
    public Flux<AutomotiveServiceSetting> findByBusinessidInc(final String id, final Date date) {
        return settingRepository.findByBusinessidInc(id,date);
    }
    public Flux<AutomotiveServiceSetting> findDynamics(final String id) {
        return settingRepository.findDynamics(id);
    }
    public Flux<AutomotiveServiceSetting> findDynamicsInc(final String id,final Date date) {
        return settingRepository.findDynamicsInc(id,date);
    }
    public Flux<AutomotiveServiceSetting> findByRegion(final String region) {
        return settingRepository.findByRegion(region);
    }

    public Flux<AutomotiveServiceSetting> findByRegionInc(final String region, final Date date) {
        return settingRepository.findByRegionInc(region,date);
    }
    public Flux<AutomotiveServiceSetting> findAllInc(final Date date) {
        return settingRepository.findAllInc(date);
    }
    public Flux<AutomotiveServiceSetting> findAll() {
        return settingRepository.findAll();
    }

}