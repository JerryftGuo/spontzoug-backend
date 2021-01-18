package com.spontzoug.server.service;

import com.spontzoug.server.model.Table;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.ITableRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class TableService implements ITableService {
    @Autowired
    private ITableRepository tableRepository;
    @Autowired
    private ISseService sseService;

    public void create(Table table)
    {
        tableRepository.save(table)
                .flatMap( rm -> {
                            return sseService.publishEnt( rm.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, rm.getBusinessid(), "table", "", "", ""));
                        }
                ).subscribe();
        //tableRepository.save(table).subscribe();
    }

    public Mono<Table> update(Table table)
    {
        return sseService.publishEnt( table.getRegion(),
                new SseEvent(SseType.SSE_ENT, table.getBusinessid(), "table", "", "", "") )
                .flatMap( bb ->{
                    return tableRepository.save(table);
                });
        //return tableRepository.save(table);
    }

    public Mono<Void> deleteById(final String id){
        return tableRepository.deleteById(id);
    }

    public Mono<Table> findById(final String id){
        return tableRepository.findById(id);
    }
    public Flux<Table> findByBusinessid(final String id) {
        return tableRepository.findByBusinessid(id);
    }
    public Flux<Table> findByBusinessidInc(final String id, final Date date) {
        return tableRepository.findByBusinessidInc(id,date);
    }
    public Flux<Table> findDynamics(final String id) {
        return tableRepository.findDynamics(id);
    }
    public Flux<Table> findDynamicsInc(final String id,final Date date) {
        return tableRepository.findDynamicsInc(id,date);
    }
}