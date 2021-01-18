package com.spontzoug.server.service;

import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.PersonalCareSetting;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IPersonalCareSettingRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class PersonalCareSettingService implements IPersonalCareSettingService {
    @Autowired
    private IPersonalCareSettingRepository settingRepository;
    @Autowired
    ISseService sseService;

    public void create(PersonalCareSetting setting)
    {
        settingRepository.save(setting)
                .flatMap( biz -> {
                            return sseService.publishEnt( setting.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, biz.getBusinessid(), "personalcaresetting", "", "", ""));
                        }
                ).subscribe();
    }

    public Mono<PersonalCareSetting> update(PersonalCareSetting setting)
    {
        return sseService.publishEnt( setting.getRegion(),
                new SseEvent(SseType.SSE_ENT, setting.getBusinessid(), "personalcaresetting", "", "", "") )
                .flatMap( bb ->{
                    return settingRepository.save(setting);
                });
        // return settingRepository.save(setting);

    }

    public Mono<Void> deleteById(final String id){
        return settingRepository.deleteById(id);
    }

    public Mono<PersonalCareSetting> findById(final String id){
        return settingRepository.findById(id);
    }
    public Flux<PersonalCareSetting> findByBusinessid(final String id) {
        return settingRepository.findByBusinessid(id);
    }
    public Flux<PersonalCareSetting> findByBusinessidInc(final String id, final Date date) {
        return settingRepository.findByBusinessidInc(id,date);
    }
    public Flux<PersonalCareSetting> findDynamics(final String id) {
        return settingRepository.findDynamics(id);
    }
    public Flux<PersonalCareSetting> findDynamicsInc(final String id,final Date date) {
        return settingRepository.findDynamicsInc(id,date);
    }
    public Flux<PersonalCareSetting> findByRegion(final String region) {
        return settingRepository.findByRegion(region);
    }

    public Flux<PersonalCareSetting> findByRegionInc(final String region, final Date date) {
        return settingRepository.findByRegionInc(region,date);
    }
    public Flux<PersonalCareSetting> findAllInc(final Date date) {
        return settingRepository.findAllInc(date);
    }
    public Flux<PersonalCareSetting> findAll() {
        return settingRepository.findAll();
    }

}