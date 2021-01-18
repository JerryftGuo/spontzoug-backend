package com.spontzoug.server.service;

import com.spontzoug.server.model.PersonalCareArtist;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IPersonalCareArtistRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class PersonalCareArtistService implements IPersonalCareArtistService {
    @Autowired
    private IPersonalCareArtistRepository personalcareartistRepository;
    @Autowired
    private ISseService sseService;

    public void create(PersonalCareArtist personalcareartist)
    {
        personalcareartistRepository.save(personalcareartist)
                .flatMap( pra -> {
                            return sseService.publishEnt( pra.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, pra.getBusinessid(), "personalcareartist", "", "", ""));
                        }
                ).subscribe();
        //    personalcareartistRepository.save(personalcareartist).subscribe();

    }
    public Mono<PersonalCareArtist> update(PersonalCareArtist personalcareartist)
    {
        return sseService.publishEnt( personalcareartist.getRegion(),
                new SseEvent(SseType.SSE_ENT, personalcareartist.getBusinessid(), "personalcareartist", "", "", "") )
                .flatMap( bb ->{
                    return personalcareartistRepository.save(personalcareartist);
                });
        //    return personalcareartistRepository.save(personalcareartist);

    }
    public Mono<Void> deleteById(final String id){
        return personalcareartistRepository.deleteById(id);
    }
    public Mono<PersonalCareArtist> findById(final String id){
        return personalcareartistRepository.findById(id);
    }
    public Flux<PersonalCareArtist> findByBusinessid(final String id){
        return personalcareartistRepository.findByBusinessid(id);
    }
    public Flux<PersonalCareArtist> findByBusinessidInc(final String id, final Date date){
        return personalcareartistRepository.findByBusinessidInc(id,date);
    }
    public Flux<PersonalCareArtist> findDynamics(final String id){
        return personalcareartistRepository.findDynamics(id);
    }
    public Flux<PersonalCareArtist> findDynamicsInc(final String id, final Date date){
        return personalcareartistRepository.findDynamicsInc(id,date);
    }
}