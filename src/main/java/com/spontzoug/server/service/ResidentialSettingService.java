package com.spontzoug.server.service;

import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.ResidentialSetting;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IResidentialSettingRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class ResidentialSettingService implements IResidentialSettingService {
    @Autowired
    private IResidentialSettingRepository settingRepository;
    @Autowired
    ISseService sseService;

    public void create(ResidentialSetting setting)
    {
        settingRepository.save(setting)
                .flatMap( biz -> {
                            return sseService.publishEnt( setting.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, biz.getBusinessid(), "residentialsetting", "", "", ""));
                        }
                ).subscribe();
    }

    public Mono<ResidentialSetting> update(ResidentialSetting setting)
    {
        return sseService.publishEnt( setting.getRegion(),
                new SseEvent(SseType.SSE_ENT, setting.getBusinessid(), "residentialsetting", "", "", "") )
                .flatMap( bb ->{
                    return settingRepository.save(setting);
                });
        // return settingRepository.save(setting);

    }

    public Mono<Void> deleteById(final String id){
        return settingRepository.deleteById(id);
    }

    public Mono<ResidentialSetting> findById(final String id){
        return settingRepository.findById(id);
    }
    public Flux<ResidentialSetting> findByBusinessid(final String id) {
        return settingRepository.findByBusinessid(id);
    }
    public Flux<ResidentialSetting> findByBusinessidInc(final String id, final Date date) {
        return settingRepository.findByBusinessidInc(id,date);
    }
    public Flux<ResidentialSetting> findDynamics(final String id) {
        return settingRepository.findDynamics(id);
    }
    public Flux<ResidentialSetting> findDynamicsInc(final String id,final Date date) {
        return settingRepository.findDynamicsInc(id,date);
    }
    public Flux<ResidentialSetting> findByRegion(final String region) {
        return settingRepository.findByRegion(region);
    }

    public Flux<ResidentialSetting> findByRegionInc(final String region, final Date date) {
        return settingRepository.findByRegionInc(region,date);
    }
    public Flux<ResidentialSetting> findAllInc(final Date date) {
        return settingRepository.findAllInc(date);
    }
    public Flux<ResidentialSetting> findAll() {
        return settingRepository.findAll();
    }

}