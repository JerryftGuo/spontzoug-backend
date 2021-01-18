package com.spontzoug.server.controller;

import com.spontzoug.server.model.AutomativeServiceAppointment;
import com.spontzoug.server.model.ProcessingSysReport;
import com.spontzoug.server.service.IAutomativeServiceAppointmentService;
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
@RequestMapping("/api/automativeserviceappointment")
public class AutomativeServiceAppointmentController {
    @Autowired
    private IAutomativeServiceAppointmentService appointmentService;
    @Autowired
    private IProcessingSysReportService processingSysReportService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AutomativeServiceAppointment appt) {
        appointmentService.create(appt);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<AutomativeServiceAppointment> update(@RequestBody AutomativeServiceAppointment m) {
        return appointmentService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return appointmentService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<AutomativeServiceAppointment>> findById(@PathVariable("id") String id) {
        Mono<AutomativeServiceAppointment> m = appointmentService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<AutomativeServiceAppointment>>(m,s);
    }

    @GetMapping("/businessid/{id}/{date1}/{date2}")
    public Flux<AutomativeServiceAppointment> findByBusinessidAndDate(
            @PathVariable("id") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return appointmentService.findByBusinessidAndDate(id,date1, date2);
    }

    @GetMapping("/businessid/{id}/{date1}/{date2}/{timestamp}")
    public Flux<AutomativeServiceAppointment> findByBusinessidAndDateInc(
            @PathVariable("id") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp){
        return appointmentService.findByBusinessidAndDateInc(id,date1, date2,timestamp);
    }

    @GetMapping("/report/day/{businessid}/{date1}/{date2}")
    public Flux<AutomativeServiceAppointment> findDayReport(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return appointmentService.findDayReport(id,date1, date2);
    }

    @GetMapping("/report/month/{businessid}/{date1}/{date2}")
    public Flux<AutomativeServiceAppointment> findMonthReport(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return appointmentService.findMonthReport(id,date1, date2);
    }


    @GetMapping("/report/day/{businessid}/{date1}/{date2}/{timestamp}")
    public Flux<AutomativeServiceAppointment> findDayReportInc(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp){
        return appointmentService.findDayReport(id,date1, date2);
    }

    @GetMapping("/report/month/{businessid}/{date1}/{date2}/{timestamp}")
    public Flux<AutomativeServiceAppointment> findMonthReportInc(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp){
        return appointmentService.findMonthReportInc(id,date1, date2,timestamp);
    }
}
