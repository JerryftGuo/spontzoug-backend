package com.spontzoug.server.controller;

import com.spontzoug.server.http.ClosingRequest;
import com.spontzoug.server.http.ClosingResponse;
import com.spontzoug.server.model.Business;
import com.spontzoug.server.redis.ISessionService;
import com.spontzoug.server.service.IBusinessAccountService;
import com.spontzoug.server.service.IBusinessService;
import com.spontzoug.server.service.IUserBusinessLinkService;
import com.spontzoug.server.service.IUserService;
import com.spontzoug.server.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/api/business")
public class BusinessController {
    @Autowired
    private IBusinessService businessService;
    @Autowired
    private IUserBusinessLinkService businessLinkService;
    @Autowired
    private IBusinessAccountService businessAccountService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ISessionService sessionService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Business b)
    {
        businessService.create(b);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Business> update(@RequestBody Business b){
        return  businessService.update(b);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById(@PathVariable("id") String id){
        return  businessService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Business>> findById(@PathVariable("id")  String id) {
        Mono<Business> b = businessService.findById(id);
        HttpStatus s = b != null ? HttpStatus.OK: HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Business>>( b, s);
    }

    @GetMapping("/id/{id}]{date}")
    public ResponseEntity<Mono<Business>> findById(
            @PathVariable("id")  String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date
    ) {
        Mono<Business> b = businessService.findByIdInc(id,date);
        HttpStatus s = b != null ? HttpStatus.OK: HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Business>>( b, s);
    }

    @GetMapping("/creator/{creator}")
    public Flux<Business> findByCreator(@PathVariable("creator") String creator){
        return businessService.findByCreator(creator);
    }

    @GetMapping("/all")
    public Flux<Business> findAll(){
        return businessService.findAll();
    }

    @GetMapping("/all/{date}")
    public Flux<Business> findAllInc(
            @PathVariable("date") Date date  ){
        return businessService.findAllInc(date);
    }

    @GetMapping("/region/{region}")
    public Flux<Business> findByRegion(
            @PathVariable("region") String region
    ){
        return businessService.findByRegion(region);
    }

    @GetMapping("/region/{region}/{date}")
    public Flux<Business> findByRegionInc(
            @PathVariable("region") String region,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date  ){
        return businessService.findByRegionInc(region,date);
    }



    @GetMapping("/dynamic/{id}")
    public Flux<Business> findDynamics(@PathVariable("id") String id){
        return businessService.findDynamics(id);
    }

    @GetMapping("/dynamic/{id}/{date}")
    public Flux<Business> findDynamicsInc(
            @PathVariable("id") String id,
            @PathVariable("date")Date date){
        return businessService.findDynamicsInc(id,date);
    }

    @GetMapping("/businessid/{id}")
    public Flux<Business> findByBusinessId(@PathVariable("id") String id){
        return businessService.findByBusinessid(id);
    }
    @GetMapping("/businessid/{id}/{date}")
    public Flux<Business> findByBusinessidInc(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return businessService.findByBusinessidInc(id,date);
    }

    @GetMapping("/report/day/{date1}/{date2}")
    public Flux<Business> findDayReport(
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return businessService.findDayReport(date1, date2);
    }

    @GetMapping("/report/month/{date1}/{date2}")
    public Flux<Business> findMonthReport(
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return businessService.findMonthReport(date1, date2);
    }

    @GetMapping("/count/{region}/{day}")
    public Mono<Long> countByRegionDay(
            @PathVariable("region") String region,
            @PathVariable("day") Integer day){
        return businessService.countByRegionDay(region,day);
    }

    @GetMapping("/billing/region/{region}/{day}")
    public Flux<Business> findByBillingDay(
            @PathVariable("region") String region,
            @PathVariable("day") Integer day){
        return businessService.findByBillingDay(region,day);
    }


    @PutMapping("/closing")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ClosingResponse> closing(@RequestBody ClosingRequest request) {
        String bid = request.getBusinessid();

        businessAccountService.findByBusinessid(bid)
        .flatMap(acnt -> {
            acnt.setClosed(true);
            acnt.setModified(Calendar.getInstance().getTime());
            return businessAccountService.update(acnt)
                    .flatMap( act ->{
                        return businessService.findById(bid)
                                .flatMap( biz -> {
                                    biz.setClosed(true);
                                    biz.setModified(Calendar.getInstance().getTime());
                                    return businessService.update(biz);
                                });
                    });
            }).subscribe();
        /*
        businessService.findById(bid)
                .flatMap( biz -> {
                    biz.setClosed(true);
                    biz.setModified(Calendar.getInstance().getTime());
                    biz.setClosedon(Calendar.getInstance().getTime());
                    return businessService.update(biz)
                        .flatMap( biz2 -> {
                            return businessAccountService.findByBusinessid(biz2.getId())
                                    .flatMap(acnt -> {
                                        acnt.setClosed(true);
                                        acnt.setClosedon(Calendar.getInstance().getTime());
                                        return businessAccountService.update(acnt);
                                    });
                        });
                })
                .subscribe();
        */

        return businessLinkService.findByBusinessid(bid)
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
                                                                return Mono.just( new ClosingResponse(uid, bid, true, ResponseCode.CLOSING_SUCCEED));
                                                            }else {
                                                                return Mono.just( new ClosingResponse(uid, bid, false, ResponseCode.CLOSING_SESSION_NOEFFECT));
                                                            }
                                                        });
                                            } else {
                                                return Mono.just( new ClosingResponse(uid, bid, true, ResponseCode.CLOSING_SUCCEED));
                                            }
                                        }).defaultIfEmpty(
                                                new ClosingResponse(uid, bid, false, ResponseCode.CLOSING_USER_NOTFOUND)
                                        );
                            });
                }).defaultIfEmpty(
                        new ClosingResponse("0", bid, false, ResponseCode.CLOSING_USER_BUSINESS_ERROR)
                );

        /*
        return businessService.findById(bid)
                .flatMap( biz ->{
                    biz.setClosed(true);
                    biz.setModified(Calendar.getInstance().getTime());
                    return businessService.update(biz)
                            .flatMap( biz1 ->{
                                return businessLinkService.findByBusinessid(bid)
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

         */
    }

}
