package com.spontzoug.server.service;

import com.spontzoug.server.model.SysSetting;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.ISysSettingRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class SysSettingService implements ISysSettingService {
    @Autowired
    private ISysSettingRepository settingRepository;
    @Autowired
    ISseService sseService;

    public void create(SysSetting setting)
    {
        settingRepository.save(setting)
                .flatMap( biz -> {
                            return sseService.publishEnt( setting.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, biz.getBusinessid(), "setting", "", "", ""));
                        }
                ).subscribe();
    }

    public Mono<SysSetting> update(SysSetting setting)
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

    public Mono<SysSetting> findById(final String id){
        return settingRepository.findById(id);
    }
    public Flux<SysSetting> findByBusinessid(final String id) {
        return settingRepository.findByBusinessid(id);
    }
    public Flux<SysSetting> findByBusinessidInc(final String id, final Date date) {
        return settingRepository.findByBusinessidInc(id,date);
    }
    public Flux<SysSetting> findDynamics(final String id) {
        return settingRepository.findDynamics(id);
    }
    public Flux<SysSetting> findDynamicsInc(final String id,final Date date) {
        return settingRepository.findDynamicsInc(id,date);
    }
}