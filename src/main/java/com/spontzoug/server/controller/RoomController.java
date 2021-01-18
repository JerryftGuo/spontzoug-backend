package com.spontzoug.server.controller;

import com.spontzoug.server.model.Accountant;
import com.spontzoug.server.model.Room;
import com.spontzoug.server.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    private IRoomService roomService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Room l) {  roomService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Room> update(@RequestBody Room m) {
        return roomService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return roomService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Room>> findById(@PathVariable("id") String id) {
        Mono<Room> m = roomService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Room>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<Room> findByBusinessid(
            @PathVariable("id") String id){
        return roomService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<Room> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return roomService.findByBusinessidInc(id,date);
    }
    @GetMapping("/dynamic/{id}")
    public Flux<Room> findDynamics(
            @PathVariable("id") String id){
        return roomService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<Room> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return roomService.findDynamicsInc(id,date);
    }
}
