package com.spontzoug.server.service;

import com.spontzoug.server.model.TaxRate;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.ITaxRateRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class TaxRateService implements ITaxRateService {
    @Autowired
    private ITaxRateRepository taxrateRepository;
    @Autowired
    ISseService sseService;

    public void create(TaxRate taxrate)
    {
        /*
        taxrateRepository.save(taxrate)
                .flatMap( biz -> {
                            return sseService.publishEnt( taxrate.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, biz.getBusinessid(), "taxrate", "", "", ""));
                        }
                ).subscribe();

         */
        taxrateRepository.save(taxrate).subscribe();
    }

    public Mono<TaxRate> update(TaxRate taxrate)
    {
        /*
        return sseService.publishEnt( taxrate.getRegion(),
                new SseEvent(SseType.SSE_ENT, taxrate.getBusinessid(), "taxrate", "", "", "") )
                .flatMap( bb ->{
                    return taxrateRepository.save(taxrate);
                });

         */
         return taxrateRepository.save(taxrate);

    }

    public Mono<Void> deleteById(final String id){
        return taxrateRepository.deleteById(id);
    }

    public Mono<TaxRate> findById(final String id){
        return taxrateRepository.findById(id);
    }
    public Flux<TaxRate> findByBusinessid(final String id) {
        return taxrateRepository.findByBusinessid(id);
    }
    public Flux<TaxRate> findByBusinessidInc(final String id, final Date date) {
        return taxrateRepository.findByBusinessidInc(id,date);
    }
    public Flux<TaxRate> findDynamics(final String id) {
        return taxrateRepository.findDynamics(id);
    }
    public Flux<TaxRate> findDynamicsInc(final String id,final Date date) {
        return taxrateRepository.findDynamicsInc(id,date);
    }
}