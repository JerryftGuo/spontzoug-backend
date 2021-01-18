package com.spontzoug.server.service;

import com.spontzoug.server.model.FinancialInsuranceService;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IFinancialInsuranceServiceRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class FinancialInsuranceServiceService implements IFinancialInsuranceServiceService {
    @Autowired
    private IFinancialInsuranceServiceRepository serviceRepository;
    @Autowired
    private ISseService sseService;

    public void create(FinancialInsuranceService service)
    {
        serviceRepository.save(service)
                .flatMap( ser -> {
                            return sseService.publishEnt( ser.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, ser.getBusinessid(), "financialinsuranceservice", "", "", ""));
                        }
                ).subscribe();
        //serviceRepository.save(service).subscribe();
    }

    public Mono<FinancialInsuranceService> update(FinancialInsuranceService service)
    {
        return sseService.publishEnt( service.getRegion(),
                new SseEvent(SseType.SSE_ENT, service.getBusinessid(), "financialinsuranceservice", "", "", "") )
                .flatMap( bb ->{
                    return serviceRepository.save(service);
                });
        //    return serviceRepository.save(service);
    }

    public Mono<Void> deleteById(final String id){
        return serviceRepository.deleteById(id);
    }

    public Mono<FinancialInsuranceService> findById(final String id){
        return serviceRepository.findById(id);
    }
    public Flux<FinancialInsuranceService> findByBusinessid(final String id) {
        return serviceRepository.findByBusinessid(id);
    }
    public Flux<FinancialInsuranceService> findByBusinessidInc(final String id,final Date date) {
        return serviceRepository.findByBusinessidInc(id,date);
    }
    public Flux<FinancialInsuranceService> findDynamics(final String id) {
        return serviceRepository.findDynamics(id);
    }
    public Flux<FinancialInsuranceService> findDynamicsInc(final String id, final Date date) {
        return serviceRepository.findDynamicsInc(id,date);
    }

    public Flux<FinancialInsuranceService> findByRegion(final String id) {
        return serviceRepository.findByRegion(id);
    }
    public Flux<FinancialInsuranceService> findByRegionInc(final String id, final Date date) {
        return serviceRepository.findByRegionInc(id,date);
    }
    public Mono<Long> countActive(final String id){
        return serviceRepository.countActive(id);
    }
}