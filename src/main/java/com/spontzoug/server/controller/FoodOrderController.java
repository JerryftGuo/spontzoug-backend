package com.spontzoug.server.controller;

import com.spontzoug.server.model.FoodOrder;
import com.spontzoug.server.model.ProcessingSysReport;
import com.spontzoug.server.service.IFoodOrderService;
import com.spontzoug.server.service.IProcessingSysReportService;
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
@RequestMapping("/api/foodorder")
public class FoodOrderController {
    @Autowired
    private IFoodOrderService orderService;
    @Autowired
    IProcessingSysReportService processingSysReportService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody FoodOrder l)
    {
        orderService.update(l)
                .flatMap( odr ->{
                    ProcessingSysReport report =
                            new ProcessingSysReport( "",
                                    odr.getRegion(),"food",
                                    odr.getCreated(),odr.getCreated(),
                                    odr.getItems(), odr.getAmount() );
                    return processingSysReportService.update(report);
                }).subscribe();
    //    orderService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<FoodOrder> update(@RequestBody FoodOrder m) {
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
    public ResponseEntity<Mono<FoodOrder>> findById(@PathVariable("id") String id) {
        Mono<FoodOrder> m = orderService.findById(id);
        HttpStatus s = m != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<FoodOrder>>(m, s);
    }

    @GetMapping("/businessid/{id}/{date1}/{date2}")
    public Flux<FoodOrder> findByBusinessidAndDate(
            @PathVariable("id") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2) {
        return orderService.findByBusinessidAndDate(id, date1, date2);
    }
    @GetMapping("/businessid/{id}/{date1}/{date2}/{timestamp}")
    public Flux<FoodOrder> findByBusinessidAndDateInc(
            @PathVariable("id") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp) {
        return orderService.findByBusinessidAndDateInc(id, date1, date2,timestamp);
    }

    @GetMapping("/clientid/{id}/{date}")
    public Flux<FoodOrder> findByClientidAndDate(
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
    public ResponseEntity<Mono<FoodOrder>> findBySerial(@PathVariable("serial") String serial) {
        Mono<FoodOrder> m = orderService.findBySerial(serial);
        HttpStatus s = m != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<FoodOrder>>(m, s);
    }
    @GetMapping("/report/day/{businessid}/{date1}/{date2}")
    public Flux<FoodOrder> findDayReport(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){

        return orderService.findDayReport(id,date1, date2);
    }

    @GetMapping("/report/month/{businessid}/{date1}/{date2}")
    public Flux<FoodOrder> findMonthReport(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){

        return orderService.findMonthReport(id,date1, date2);
    }

    @GetMapping("/report/day/{businessid}/{date1}/{date2}/{timestamp}")
    public Flux<FoodOrder> findDayReport(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp){

        return orderService.findDayReportInc(id,date1, date2,timestamp);
    }

    @GetMapping("/report/month/{businessid}/{date1}/{date2}/{timestamp}")
    public Flux<FoodOrder> findMonthReportInc(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp){

        return orderService.findMonthReportInc(id,date1, date2,timestamp);
    }

}
