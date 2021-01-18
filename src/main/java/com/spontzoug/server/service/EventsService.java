package com.spontzoug.server.service;

import com.spontzoug.server.model.Events;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IEventsRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class EventsService implements IEventsService {
    @Autowired
    private IEventsRepository eventsRepository;
    @Autowired
    private ISseService sseService;

    public void create(Events service)
    {
        eventsRepository.save(service).subscribe();
    }

    public Mono<Events> update(Events service)
    {
        return eventsRepository.save(service);
    }

    public Mono<Void> deleteById(final String id){
        return eventsRepository.deleteById(id);
    }

    public Mono<Events> findById(final String id){
        return eventsRepository.findById(id);
    }
    public Flux<Events> findByBusinessid(final String id) {
        return eventsRepository.findByBusinessid(id);
    }
    public Flux<Events> findByBusinessidInc(final String id,final Date date) {
        return eventsRepository.findByBusinessidInc(id,date);
    }
    public Flux<Events> findDynamics(final String id) {
        return eventsRepository.findDynamics(id);
    }
    public Flux<Events> findDynamicsInc(final String id, final Date date) {
        return eventsRepository.findDynamicsInc(id,date);
    }

    public Flux<Events> findByRegion(final String id) {
        return eventsRepository.findByRegion(id);
    }
    public Flux<Events> findByRegionInc(final String id, final Date date) {
        return eventsRepository.findByRegionInc(id,date);
    }
}