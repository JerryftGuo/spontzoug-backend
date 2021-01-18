package com.spontzoug.server.controller;

import com.spontzoug.server.model.Order;
import com.spontzoug.server.service.IOrderService;
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
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Order l) {
        orderService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Order> update(@RequestBody Order m) {
        log.info("put order");
        return orderService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById(@PathVariable("id") String id) {
        log.info("del order");
        return orderService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Order>> findById(@PathVariable("id") String id) {
        Mono<Order> m = orderService.findById(id);
        HttpStatus s = m != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Order>>(m, s);
    }

    @GetMapping("/businessid/{id}/{date}")
    public Flux<Order> findByBusinessidAndDate(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date date1 = cal.getTime();
        Date date2 = new Date(date1.getTime() + 86400000);
        log.info(" get appt called:" + date1 + "  " + date2 + " " + id);
        return orderService.findByBusinessidAndDate(id, date1, date2);
    }

    @GetMapping("/clientid/{id}/{date}")
    public Flux<Order> findByClientidAndDate(
            @PathVariable("id") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date date1 = cal.getTime();
        Date date2 = new Date(date1.getTime() + 86400000);
        log.info(" get appt called:" + date1 + "  " + date2 + " " + id);
        return orderService.findByClientidAndDate(id, date1, date2);
    }

    @GetMapping("/serial/{serial}")
    public ResponseEntity<Mono<Order>> findBySerial(@PathVariable("serial") String serial) {
        Mono<Order> m = orderService.findBySerial(serial);
        HttpStatus s = m != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Order>>(m, s);
    }
    @GetMapping("/report/day/{businessid}/{date}")
    public Flux<Order> findDayReport(
            @PathVariable("businessid") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dt){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        Date date1 = cal.getTime();
        Date date2 = new Date( date1.getTime()+ 86400000 );
        log.info(" get report called:"+ date1 + "  "+ date2+ " "+id);
        return orderService.findDayReport(id,date1, date2);
    }

    @GetMapping("/report/month/{businessid}/{date}")
    public Flux<Order> findMonthReport(
            @PathVariable("businessid") String id,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dt){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH,1);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        Date date1 = cal.getTime();
        int m = cal.get(Calendar.MONTH);
        if( m == 11){
            int y= cal.get(Calendar.YEAR);
            cal.set(Calendar.YEAR,y+1);
            cal.set(Calendar.MONTH,0);
        }else{
            cal.set(Calendar.MONTH,m+1);
        }
        Date date2 = cal.getTime();
        log.info(" get report called:"+ date1 + "  "+ date2+ " "+id);
        return orderService.findMonthReport(id,date1, date2);
    }

}
