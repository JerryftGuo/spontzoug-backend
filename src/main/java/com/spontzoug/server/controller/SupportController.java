package com.spontzoug.server.controller;

import com.spontzoug.server.http.ClosingRequest;
import com.spontzoug.server.http.ClosingResponse;
import com.spontzoug.server.model.Support;
import com.spontzoug.server.redis.ISessionService;
import com.spontzoug.server.service.ISupportService;
import com.spontzoug.server.service.IUserBusinessLinkService;
import com.spontzoug.server.service.IUserService;
import com.spontzoug.server.util.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Calendar;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/support")
public class SupportController {
    @Autowired
    private ISupportService supportService;
    @Autowired
    private IUserBusinessLinkService businessLinkService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ISessionService sessionService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Support l) {  supportService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Support> update(@RequestBody Support m) {
        return supportService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return supportService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Support>> findById(@PathVariable("id") String id) {
        Mono<Support> m = supportService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Support>>(m,s);
    }

    @GetMapping("/businessid/{id}")
    public Flux<Support> findByBusinessid(@PathVariable("id") String id){
        return supportService.findByBusinessid(id);
    }
    @GetMapping("/businessconfig/{id}")
    public Flux<Support> findByBusinessConf(@PathVariable("id") String id){
        return supportService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<Support> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return supportService.findByBusinessidInc(id,date);
    }

    @GetMapping("/dynamic/{id}")
    public Flux<Support> findDynamics(@PathVariable("id") String id){
        return supportService.findDynamics(id);
    }
    @GetMapping("/dynamic/{id}/{date}")
    public Flux<Support> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return supportService.findDynamicsInc(id,date);
    }
    
    @PutMapping("/closing")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ClosingResponse> closing(@RequestBody ClosingRequest request) {
        String id = request.getId();
        String bid = request.getBusinessid();
        return supportService.findById(id)
                .flatMap( user ->{
                    user.setClosed(true);
                    user.setModified(Calendar.getInstance().getTime());
                    return supportService.update(user)
                            .flatMap( user1 ->{
                                return businessLinkService.findByBusinessStaffid(bid,id)
                                        .flatMap( ul ->{
                                            ul.getRole().setActive(false);
                                            return businessLinkService.update(ul)
                                                    .flatMap( ul2 ->{
                                                        String uid = ul2.getUserid();
                                                        return userService.findById(uid)
                                                                .flatMap( usr ->{
                                                                    if ( usr.getSignedin()){
                                                                        return sessionService.delUserSession(usr.getToken(),usr.getId())
                                                                                .flatMap( res ->{
                                                                                    if( res.booleanValue()){
                                                                                        return Mono.just( new ClosingResponse(id, bid, true, ResponseCode.CLOSING_SUCCEED));
                                                                                    }else {
                                                                                        return Mono.just( new ClosingResponse(id, bid, false, ResponseCode.CLOSING_SESSION_NOEFFECT));
                                                                                    }
                                                                                });
                                                                    } else {
                                                                        return Mono.just( new ClosingResponse(id, bid, true, ResponseCode.CLOSING_SUCCEED));
                                                                    }
                                                                }).defaultIfEmpty(
                                                                        new ClosingResponse(id, bid, false, ResponseCode.CLOSING_USER_NOTFOUND)
                                                                );
                                                    });
                                        }).defaultIfEmpty(
                                                new ClosingResponse(id, bid, false, ResponseCode.CLOSING_USER_BUSINESS_ERROR)
                                        );
                            });
                }).defaultIfEmpty(
                        new ClosingResponse(id, bid, false, ResponseCode.CLOSING_ACCOUNT_ERROR)
                );
    }
}
