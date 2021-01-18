package com.spontzoug.server.controller;

import com.spontzoug.server.redis.IInvitationService;
import com.spontzoug.server.http.*;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.PaymentMethod;
import com.spontzoug.server.annotation.*;

import com.spontzoug.server.redis.ISseService;
import com.spontzoug.server.util.SseChannel;
import com.spontzoug.server.util.SseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;


@RestController
@RequestMapping("/sse")
public class SseController {
    @Autowired
    private ISseService sseService;
/*
    @GetMapping(value="/gen/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Long> gensse(
            @PathVariable("id") String id) {
        return sseService.publishEnt(SseChannel.SSE_CHANNEL_ENT, new SseEvent("city",id,"",""));
    }

 */

    @GetMapping(value="/ent/{region}/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<SseEvent>> entsse(
            @PathVariable("region") String region,
            @PathVariable("id") String bizid) {

        return sseService.entSubscribe(region,bizid)
                .map(ev -> ServerSentEvent.<SseEvent>builder()
                        .id(Long.toString(new Date().getTime()))
                        .event("sse")
                        .data(ev)
                        .build());
    }

    @GetMapping(value="/cust/{region}/{uid}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<SseEvent>> custsse(
            @PathVariable("region") String region,
            @PathVariable("uid") String uid) {
        return sseService.custSubscribe(region, uid)
                .map(ev -> ServerSentEvent.<SseEvent>builder()
                        .id(Long.toString(new Date().getTime()))
                        .event("sse")
                        .data(ev)
                        .build());
    }

        /*
        return Flux.interval(Duration.ofSeconds(2))
                .map(seq -> ServerSentEvent.<String>builder()
                        .id(id)
                        .event("periodic-envent")
                        .data("sse-"+LocalTime.now().toString())
                        .build());
         */

}
