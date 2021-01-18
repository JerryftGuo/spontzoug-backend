package com.spontzoug.server.service;

import com.spontzoug.server.model.TrainingCoach;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.ITrainingCoachRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class TrainingCoachService implements ITrainingCoachService {
    @Autowired
    private ITrainingCoachRepository trainingcoachRepository;
    @Autowired
    private ISseService sseService;

    public void create(TrainingCoach trainingcoach)
    {
        trainingcoachRepository.save(trainingcoach)
                .flatMap( pra -> {
                            return sseService.publishEnt( pra.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, pra.getBusinessid(), "trainingcoach", "", "", ""));
                        }
                ).subscribe();
        //    trainingcoachRepository.save(trainingcoach).subscribe();

    }
    public Mono<TrainingCoach> update(TrainingCoach trainingcoach)
    {
        return sseService.publishEnt( trainingcoach.getRegion(),
                new SseEvent(SseType.SSE_ENT, trainingcoach.getBusinessid(), "trainingcoach", "", "", "") )
                .flatMap( bb ->{
                    return trainingcoachRepository.save(trainingcoach);
                });
        //    return trainingcoachRepository.save(trainingcoach);

    }
    public Mono<Void> deleteById(final String id){
        return trainingcoachRepository.deleteById(id);
    }
    public Mono<TrainingCoach> findById(final String id){
        return trainingcoachRepository.findById(id);
    }
    public Flux<TrainingCoach> findByBusinessid(final String id){
        return trainingcoachRepository.findByBusinessid(id);
    }
    public Flux<TrainingCoach> findByBusinessidInc(final String id, final Date date){
        return trainingcoachRepository.findByBusinessidInc(id,date);
    }
    public Flux<TrainingCoach> findDynamics(final String id){
        return trainingcoachRepository.findDynamics(id);
    }
    public Flux<TrainingCoach> findDynamicsInc(final String id, final Date date){
        return trainingcoachRepository.findDynamicsInc(id,date);
    }
}