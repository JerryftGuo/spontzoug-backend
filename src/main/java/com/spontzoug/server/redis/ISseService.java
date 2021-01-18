package com.spontzoug.server.redis;

import com.spontzoug.server.util.SseEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISseService {
    Flux<SseEvent> entSubscribe(final String region, final String bizid);
    Flux<SseEvent> custSubscribe(final String region, final String uid);
    Mono<Long> publishEnt (final String region,final SseEvent event);
    Mono<Long> publishMesg (final String region,final SseEvent event);
}