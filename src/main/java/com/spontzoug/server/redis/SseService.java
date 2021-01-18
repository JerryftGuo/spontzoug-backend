package com.spontzoug.server.redis;

import com.spontzoug.server.util.SseChannel;
import com.spontzoug.server.util.SseEvent;
import com.spontzoug.server.util.SseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class SseService implements ISseService {
    @Autowired
    private ReactiveRedisTemplate<String, SseEvent> sseTemplate;

    public Mono<Long> publishEnt (String region, SseEvent event){
        return sseTemplate.convertAndSend(getEntChannel(region), event);
    }

    public Mono<Long> publishMesg (String region, SseEvent event){
        return sseTemplate.convertAndSend(getMesgChannel(region), event);
    }

    public Flux<SseEvent> entSubscribe ( String region,String bizid){

        log.info("sse ent called:"+ region + bizid);
        return sseTemplate.listenToChannel( getEntChannel(region))
                .map( ev -> ev.getMessage())
                .filter( mesg -> mesg.getBizid().equals(bizid));
    }

    public Flux<SseEvent> custSubscribe ( String region, String uid){
        return sseTemplate.listenToChannel( getEntChannel(region), getMesgChannel(region))
                .map( ev -> ev.getMessage())
                .filter( mesg -> {
                    return  (mesg.getType().equals(SseType.SSE_MESG) &&
                            mesg.getTarid().equals(uid) ) ||
                            mesg.getType().equals(SseType.SSE_ENT) ;
                });
    }

    private String getEntChannel(String region){
        return SseType.SSE_PRE_ENT +region;
    }

    private String getMesgChannel(String region){
        return SseType.SSE_PRE_MESG+region;
    }

}