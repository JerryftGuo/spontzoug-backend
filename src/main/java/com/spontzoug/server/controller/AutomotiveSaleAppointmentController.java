package com.spontzoug.server.controller;

import com.spontzoug.server.model.AutomotiveSaleAppointment;
import com.spontzoug.server.service.IAutomotiveSaleAppointmentService;
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
@RequestMapping("/api/automotivesaleappointment")
public class AutomotiveSaleAppointmentController {
    @Autowired
    private IAutomotiveSaleAppointmentService appointmentService;
    @Autowired
    private IProcessingSysReportService processingSysReportService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AutomotiveSaleAppointment appt) {
        appointmentService.create(appt);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<AutomotiveSaleAppointment> update(@RequestBody AutomotiveSaleAppointment m) {
        return appointmentService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return appointmentService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<AutomotiveSaleAppointment>> findById(@PathVariable("id") String id) {
        Mono<AutomotiveSaleAppointment> m = appointmentService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<AutomotiveSaleAppointment>>(m,s);
    }

    @GetMapping("/businessid/{id}/{date1}/{date2}")
    public Flux<AutomotiveSaleAppointment> findByBusinessidAndDate(
            @PathVariable("id") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return appointmentService.findByBusinessidAndDate(id,date1, date2);
    }

    @GetMapping("/businessid/{id}/{date1}/{date2}/{timestamp}")
    public Flux<AutomotiveSaleAppointment> findByBusinessidAndDateInc(
            @PathVariable("id") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp){
        return appointmentService.findByBusinessidAndDateInc(id,date1, date2,timestamp);
    }

    @GetMapping("/report/day/{businessid}/{date1}/{date2}")
    public Flux<AutomotiveSaleAppointment> findDayReport(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return appointmentService.findDayReport(id,date1, date2);
    }

    @GetMapping("/report/month/{businessid}/{date1}/{date2}")
    public Flux<AutomotiveSaleAppointment> findMonthReport(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return appointmentService.findMonthReport(id,date1, date2);
    }


    @GetMapping("/report/day/{businessid}/{date1}/{date2}/{timestamp}")
    public Flux<AutomotiveSaleAppointment> findDayReportInc(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp){
        return appointmentService.findDayReport(id,date1, date2);
    }

    @GetMapping("/report/month/{businessid}/{date1}/{date2}/{timestamp}")
    public Flux<AutomotiveSaleAppointment> findMonthReportInc(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp){
        return appointmentService.findMonthReportInc(id,date1, date2,timestamp);
    }
}
