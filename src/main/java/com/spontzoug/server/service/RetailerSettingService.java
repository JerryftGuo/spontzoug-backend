package com.spontzoug.server.service;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.RetailerSetting;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IRetailerSettingRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class RetailerSettingService implements IRetailerSettingService {
    @Autowired
    private IRetailerSettingRepository settingRepository;
    @Autowired
    ISseService sseService;

    public void create(RetailerSetting setting)
    {
        settingRepository.save(setting)
                .flatMap( biz -> {
                            return sseService.publishEnt( setting.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, biz.getBusinessid(), "setting", "", "", ""));
                        }
                ).subscribe();
    }

    public Mono<RetailerSetting> update(RetailerSetting setting)
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

    public Mono<RetailerSetting> findById(final String id){
        return settingRepository.findById(id);
    }
    public Flux<RetailerSetting> findByBusinessid(final String id) {
        return settingRepository.findByBusinessid(id);
    }
    public Flux<RetailerSetting> findByBusinessidInc(final String id, final Date date) {
        return settingRepository.findByBusinessidInc(id,date);
    }
    public Flux<RetailerSetting> findDynamics(final String id) {
        return settingRepository.findDynamics(id);
    }
    public Flux<RetailerSetting> findDynamicsInc(final String id,final Date date) {
        return settingRepository.findDynamicsInc(id,date);
    }

    public Flux<RetailerSetting> findByRegion(final String region) {
        return settingRepository.findByRegion(region);
    }

    public Flux<RetailerSetting> findByRegionInc(final String region, final Date date) {
        return settingRepository.findByRegionInc(region,date);
    }
    public Flux<RetailerSetting> findAllInc(final Date date) {
        return settingRepository.findAllInc(date);
    }
    public Flux<RetailerSetting> findAll() {
        return settingRepository.findAll();
    }
}