package com.spontzoug.server.service;

import com.spontzoug.server.model.FoodSetting;
import com.spontzoug.server.model.FinancialInsuranceSetting;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IFinancialInsuranceSettingRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class FinancialInsuranceSettingService implements IFinancialInsuranceSettingService {
    @Autowired
    private IFinancialInsuranceSettingRepository settingRepository;
    @Autowired
    ISseService sseService;

    public void create(FinancialInsuranceSetting setting)
    {
        settingRepository.save(setting)
                .flatMap( biz -> {
                            return sseService.publishEnt( setting.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, biz.getBusinessid(), "financialinsurancesetting", "", "", ""));
                        }
                ).subscribe();
    }

    public Mono<FinancialInsuranceSetting> update(FinancialInsuranceSetting setting)
    {
        return sseService.publishEnt( setting.getRegion(),
                new SseEvent(SseType.SSE_ENT, setting.getBusinessid(), "financialinsurancesetting", "", "", "") )
                .flatMap( bb ->{
                    return settingRepository.save(setting);
                });
        // return settingRepository.save(setting);

    }

    public Mono<Void> deleteById(final String id){
        return settingRepository.deleteById(id);
    }

    public Mono<FinancialInsuranceSetting> findById(final String id){
        return settingRepository.findById(id);
    }
    public Flux<FinancialInsuranceSetting> findByBusinessid(final String id) {
        return settingRepository.findByBusinessid(id);
    }
    public Flux<FinancialInsuranceSetting> findByBusinessidInc(final String id, final Date date) {
        return settingRepository.findByBusinessidInc(id,date);
    }
    public Flux<FinancialInsuranceSetting> findDynamics(final String id) {
        return settingRepository.findDynamics(id);
    }
    public Flux<FinancialInsuranceSetting> findDynamicsInc(final String id,final Date date) {
        return settingRepository.findDynamicsInc(id,date);
    }
    public Flux<FinancialInsuranceSetting> findByRegion(final String region) {
        return settingRepository.findByRegion(region);
    }

    public Flux<FinancialInsuranceSetting> findByRegionInc(final String region, final Date date) {
        return settingRepository.findByRegionInc(region,date);
    }
    public Flux<FinancialInsuranceSetting> findAllInc(final Date date) {
        return settingRepository.findAllInc(date);
    }
    public Flux<FinancialInsuranceSetting> findAll() {
        return settingRepository.findAll();
    }

}