package com.spontzoug.server.service;

import com.spontzoug.server.model.FinancialInsuranceStaff;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IFinancialInsuranceStaffRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class FinancialInsuranceStaffService implements IFinancialInsuranceStaffService {
    @Autowired
    private IFinancialInsuranceStaffRepository financialinsurancestaffRepository;
    @Autowired
    private ISseService sseService;

    public void create(FinancialInsuranceStaff financialinsurancestaff)
    {
        financialinsurancestaffRepository.save(financialinsurancestaff)
                .flatMap( pra -> {
                            return sseService.publishEnt( pra.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, pra.getBusinessid(), "financialinsurancestaff", "", "", ""));
                        }
                ).subscribe();
        //    financialinsurancestaffRepository.save(financialinsurancestaff).subscribe();

    }
    public Mono<FinancialInsuranceStaff> update(FinancialInsuranceStaff financialinsurancestaff)
    {
        return sseService.publishEnt( financialinsurancestaff.getRegion(),
                new SseEvent(SseType.SSE_ENT, financialinsurancestaff.getBusinessid(), "financialinsurancestaff", "", "", "") )
                .flatMap( bb ->{
                    return financialinsurancestaffRepository.save(financialinsurancestaff);
                });
        //    return financialinsurancestaffRepository.save(financialinsurancestaff);

    }
    public Mono<Void> deleteById(final String id){
        return financialinsurancestaffRepository.deleteById(id);
    }
    public Mono<FinancialInsuranceStaff> findById(final String id){
        return financialinsurancestaffRepository.findById(id);
    }
    public Flux<FinancialInsuranceStaff> findByBusinessid(final String id){
        return financialinsurancestaffRepository.findByBusinessid(id);
    }
    public Flux<FinancialInsuranceStaff> findByBusinessidInc(final String id, final Date date){
        return financialinsurancestaffRepository.findByBusinessidInc(id,date);
    }
    public Flux<FinancialInsuranceStaff> findDynamics(final String id){
        return financialinsurancestaffRepository.findDynamics(id);
    }
    public Flux<FinancialInsuranceStaff> findDynamicsInc(final String id, final Date date){
        return financialinsurancestaffRepository.findDynamicsInc(id,date);
    }
}