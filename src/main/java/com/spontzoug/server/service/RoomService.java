package com.spontzoug.server.service;

import com.spontzoug.server.model.Room;
import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.repository.IRoomRepository;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class RoomService implements IRoomService {
    @Autowired
    private IRoomRepository roomRepository;
    @Autowired
    private ISseService sseService;

    public void create(Room room)
    {
        roomRepository.save(room)
                .flatMap( rm -> {
                            return sseService.publishEnt( rm.getRegion(),
                                    new SseEvent(SseType.SSE_ENT, rm.getBusinessid(), "room", "", "", ""));
                        }
                ).subscribe();
        //roomRepository.save(room).subscribe();
    }

    public Mono<Room> update(Room room)
    {
        return sseService.publishEnt( room.getRegion(),
                new SseEvent(SseType.SSE_ENT, room.getBusinessid(), "room", "", "", "") )
                .flatMap( bb ->{
                    return roomRepository.save(room);
                });
        //return roomRepository.save(room);
    }

    public Mono<Void> deleteById(final String id){
        return roomRepository.deleteById(id);
    }

    public Mono<Room> findById(final String id){
        return roomRepository.findById(id);
    }
    public Flux<Room> findByBusinessid(final String id) {
        return roomRepository.findByBusinessid(id);
    }
    public Flux<Room> findByBusinessidInc(final String id, final Date date) {
        return roomRepository.findByBusinessidInc(id,date);
    }
    public Flux<Room> findDynamics(final String id) {
        return roomRepository.findDynamics(id);
    }
    public Flux<Room> findDynamicsInc(final String id,final Date date) {
        return roomRepository.findDynamicsInc(id,date);
    }
}