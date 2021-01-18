package com.spontzoug.server.service;

import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.TrainingSetting;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.ITrainingSettingRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class TrainingSettingService implements ITrainingSettingService {
    @Autowired
    private ITrainingSettingRepository settingRepository;
    @Autowired
    ISseService sseService;

    public void create(TrainingSetting setting)
    {
        settingRepository.save(setting)
                .flatMap( biz -> {
                            return sseService.publishEnt( setting.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, biz.getBusinessid(), "trainingcaresetting", "", "", ""));
                        }
                ).subscribe();
    }

    public Mono<TrainingSetting> update(TrainingSetting setting)
    {
        return sseService.publishEnt( setting.getRegion(),
                new SseEvent(SseType.SSE_ENT, setting.getBusinessid(), "trainingsetting", "", "", "") )
                .flatMap( bb ->{
                    return settingRepository.save(setting);
                });
        // return settingRepository.save(setting);

    }

    public Mono<Void> deleteById(final String id){
        return settingRepository.deleteById(id);
    }

    public Mono<TrainingSetting> findById(final String id){
        return settingRepository.findById(id);
    }
    public Flux<TrainingSetting> findByBusinessid(final String id) {
        return settingRepository.findByBusinessid(id);
    }
    public Flux<TrainingSetting> findByBusinessidInc(final String id, final Date date) {
        return settingRepository.findByBusinessidInc(id,date);
    }
    public Flux<TrainingSetting> findDynamics(final String id) {
        return settingRepository.findDynamics(id);
    }
    public Flux<TrainingSetting> findDynamicsInc(final String id,final Date date) {
        return settingRepository.findDynamicsInc(id,date);
    }
    public Flux<TrainingSetting> findByRegion(final String region) {
        return settingRepository.findByRegion(region);
    }

    public Flux<TrainingSetting> findByRegionInc(final String region, final Date date) {
        return settingRepository.findByRegionInc(region,date);
    }
    public Flux<TrainingSetting> findAllInc(final Date date) {
        return settingRepository.findAllInc(date);
    }
    public Flux<TrainingSetting> findAll() {
        return settingRepository.findAll();
    }

}