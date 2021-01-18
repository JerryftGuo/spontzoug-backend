package com.spontzoug.server.service;

import com.spontzoug.server.model.Assistant;
import com.spontzoug.server.repository.IAssistantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class AssistantService implements IAssistantService {
    @Autowired
    private IAssistantRepository assistantRepository;

    public void create(Assistant assistant){
        assistantRepository.save(assistant).subscribe();
    }
    public Mono<Assistant> update(Assistant assistant){
        return assistantRepository.save(assistant);
    }
    public Mono<Void> deleteById(final String id){
        return assistantRepository.deleteById(id);
    }
    public Mono<Assistant> findById(final String id){
        return assistantRepository.findById(id);
    }
    public Flux<Assistant> findByBusinessid(final String id)
    {
        return assistantRepository.findByBusinessid(id);
    }
    public Flux<Assistant> findByBusinessidInc(final String id, final Date date)
    {
        return assistantRepository.findByBusinessidInc(id,date);
    }
    public Flux<Assistant> findDynamics(final String id){
        return assistantRepository.findDynamics(id);
    }
    public Flux<Assistant> findDynamicsInc(final String id, final Date date){
        return assistantRepository.findDynamicsInc(id,date);
    }
}