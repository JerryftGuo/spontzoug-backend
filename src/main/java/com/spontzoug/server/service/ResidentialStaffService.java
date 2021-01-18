package com.spontzoug.server.service;

import com.spontzoug.server.model.ResidentialStaff;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IResidentialStaffRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class ResidentialStaffService implements IResidentialStaffService {
    @Autowired
    private IResidentialStaffRepository residentialstaffRepository;
    @Autowired
    private ISseService sseService;

    public void create(ResidentialStaff residentialstaff)
    {
        residentialstaffRepository.save(residentialstaff)
                .flatMap( pra -> {
                            return sseService.publishEnt( pra.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, pra.getBusinessid(), "residentialstaff", "", "", ""));
                        }
                ).subscribe();
        //    residentialstaffRepository.save(residentialstaff).subscribe();

    }
    public Mono<ResidentialStaff> update(ResidentialStaff residentialstaff)
    {
        return sseService.publishEnt( residentialstaff.getRegion(),
                new SseEvent(SseType.SSE_ENT, residentialstaff.getBusinessid(), "residentialstaff", "", "", "") )
                .flatMap( bb ->{
                    return residentialstaffRepository.save(residentialstaff);
                });
        //    return residentialstaffRepository.save(residentialstaff);

    }
    public Mono<Void> deleteById(final String id){
        return residentialstaffRepository.deleteById(id);
    }
    public Mono<ResidentialStaff> findById(final String id){
        return residentialstaffRepository.findById(id);
    }
    public Flux<ResidentialStaff> findByBusinessid(final String id){
        return residentialstaffRepository.findByBusinessid(id);
    }
    public Flux<ResidentialStaff> findByBusinessidInc(final String id, final Date date){
        return residentialstaffRepository.findByBusinessidInc(id,date);
    }
    public Flux<ResidentialStaff> findDynamics(final String id){
        return residentialstaffRepository.findDynamics(id);
    }
    public Flux<ResidentialStaff> findDynamicsInc(final String id, final Date date){
        return residentialstaffRepository.findDynamicsInc(id,date);
    }
}