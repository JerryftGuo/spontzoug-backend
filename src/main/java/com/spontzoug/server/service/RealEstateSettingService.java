package com.spontzoug.server.service;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.RealEstateSetting;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IRealEstateSettingRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class RealEstateSettingService implements IRealEstateSettingService {
    @Autowired
    private IRealEstateSettingRepository settingRepository;
    @Autowired
    ISseService sseService;

    public void create(RealEstateSetting setting)
    {
        settingRepository.save(setting)
                .flatMap( biz -> {
                            return sseService.publishEnt( setting.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, biz.getBusinessid(), "realestatesetting", "", "", ""));
                        }
                ).subscribe();
    }

    public Mono<RealEstateSetting> update(RealEstateSetting setting)
    {
        return sseService.publishEnt( setting.getRegion(),
                new SseEvent(SseType.SSE_ENT, setting.getBusinessid(), "realestatesetting", "", "", "") )
                .flatMap( bb ->{
                    return settingRepository.save(setting);
                });
        // return settingRepository.save(setting);

    }

    public Mono<Void> deleteById(final String id){
        return settingRepository.deleteById(id);
    }

    public Mono<RealEstateSetting> findById(final String id){
        return settingRepository.findById(id);
    }
    public Flux<RealEstateSetting> findByBusinessid(final String id) {
        return settingRepository.findByBusinessid(id);
    }
    public Flux<RealEstateSetting> findByBusinessidInc(final String id, final Date date) {
        return settingRepository.findByBusinessidInc(id,date);
    }
    public Flux<RealEstateSetting> findDynamics(final String id) {
        return settingRepository.findDynamics(id);
    }
    public Flux<RealEstateSetting> findDynamicsInc(final String id,final Date date) {
        return settingRepository.findDynamicsInc(id,date);
    }

    public Flux<RealEstateSetting> findByRegion(final String region) {
        return settingRepository.findByRegion(region);
    }

    public Flux<RealEstateSetting> findByRegionInc(final String region, final Date date) {
        return settingRepository.findByRegionInc(region,date);
    }
    public Flux<RealEstateSetting> findAllInc(final Date date) {
        return settingRepository.findAllInc(date);
    }
    public Flux<RealEstateSetting> findAll() {
        return settingRepository.findAll();
    }
}