package com.spontzoug.server.service;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IBusinessRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class BusinessService implements IBusinessService {
    @Autowired
    private IBusinessRepository businessRep;
    @Autowired
    private ISseService sseService;

    public void  create(Business b){
            businessRep.save(b)
                    .flatMap( biz -> {
                           return sseService.publishEnt( biz.getAddress().getProvince(),
                                        new SseEvent(SseType.SSE_ENT, biz.getId(), "business", "", "", ""));
                            }
                    ).subscribe();
    }
    public Mono<Business> update(Business b) {

           return sseService.publishEnt( b.getAddress().getProvince(),
                 new SseEvent(SseType.SSE_ENT, b.getId(), "business", "", "", "") )
                 .flatMap( bb ->{
                                return businessRep.save(b);
                 });

        /*
        return businessRep.save(b).flatMap( biz -> {
                    return sseService.publishEnt( biz.getAddress().getProvince(),
                            new SseEvent(SseType.SSE_ENT, biz.getId(), "business", "", "", "") )
                            .map( bb -> b);
                }
        );

         */
    }

    public Mono<Void> deleteById(String id) {
        return businessRep.deleteById(id);
    }

    public Mono<Business> findById(final  String id){
        return businessRep.findById(id);
    }
    public Mono<Business> findByIdInc(final  String id, final Date date)
    {
        return businessRep.findByIdInc(id, date);
    }

    public Flux<Business> findByRegion(final String region) {
        return businessRep.findByRegion(region);
    }

    public Flux<Business> findByRegionInc(final String region, final Date date) {
        return businessRep.findByRegionInc(region,date);
    }
    public Flux<Business> findAllInc(final Date date) {
        return businessRep.findAllInc(date);
    }

    public Flux<Business> findByCreator(final String creator){
        return businessRep.findByCreator(creator);
    }

    public Flux<Business> findAll(){
        return businessRep.findAll();
    }
    public Flux<Business> findDynamics(final  String id){
        return businessRep.findDynamics(id);
    }
    public Flux<Business> findDynamicsInc(final  String id, final Date date){
        return businessRep.findDynamicsInc(id,date);
    }
    public Flux<Business> findByBusinessid(final String id) {
        return businessRep.findByBusinessid(id);
    }
    public Flux<Business> findByBusinessidInc(final String id, final Date date) {
        return businessRep.findByBusinessidInc(id,date);
    }
    public Flux<Business> findDayReport(
            final Date date1, final Date date2){
        return businessRep.findDayReport(date1, date2);
    }
    public Flux<Business> findMonthReport(
            final Date date1, final Date date2){
        return businessRep.findMonthReport(date1, date2);
    }
    public Mono<Long> countByRegionDay(
            final String region, final Integer day){
        return businessRep.countByRegionDay(region, day);
    }
    public Flux<Business> findByBillingDay(final String region, final Integer day) {
        return businessRep.findByBillingDay(region,day);
    }
}