package com.spontzoug.server.controller;

import com.spontzoug.server.model.Reservation;
import com.spontzoug.server.model.ProcessingSysReport;
import com.spontzoug.server.service.IReservationService;
import com.spontzoug.server.service.IProcessingSysReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    private IReservationService reservationService;
    @Autowired
    private IProcessingSysReportService processingSysReportService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Reservation appt) {
        reservationService.update(appt)
                .flatMap( apt ->{
                    ProcessingSysReport report =
                            new ProcessingSysReport( "",
                                    apt.getRegion(),"reservation",
                                    apt.getStart(),apt.getStart(),1, BigDecimal.ZERO);
                    return processingSysReportService.update(report);
                }).subscribe();
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Reservation> update(@RequestBody Reservation m) {
        return reservationService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return reservationService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Reservation>> findById(@PathVariable("id") String id) {
        Mono<Reservation> m = reservationService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Reservation>>(m,s);
    }

    @GetMapping("/businessid/{id}/{date1}/{date2}")
    public Flux<Reservation> findByBusinessidAndDate(
            @PathVariable("id") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return reservationService.findByBusinessidAndDate(id,date1, date2);
    }

    @GetMapping("/businessid/{id}/{date1}/{date2}/{timestamp}")
    public Flux<Reservation> findByBusinessidAndDateInc(
            @PathVariable("id") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp){
        return reservationService.findByBusinessidAndDateInc(id,date1, date2,timestamp);
    }

}
