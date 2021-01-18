package com.spontzoug.server.controller;

import com.spontzoug.server.model.ClinicAppointment;
import com.spontzoug.server.service.IClinicAppointmentService;
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
@RequestMapping("/api/clinicappointment")
public class ClinicAppointmentController {
    @Autowired
    private IClinicAppointmentService appointmentService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ClinicAppointment l) {
        log.info("clinic appontment called");
        appointmentService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ClinicAppointment> update(@RequestBody ClinicAppointment m) {
        return appointmentService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return appointmentService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<ClinicAppointment>> findById(@PathVariable("id") String id) {
        Mono<ClinicAppointment> m = appointmentService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<ClinicAppointment>>(m,s);
    }

    @GetMapping("/businessid/{id}/{date1}/{date2}")
    public Flux<ClinicAppointment> findByBusinessidAndDate(
            @PathVariable("id") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return appointmentService.findByBusinessidAndStartAfterAndStartBefore(id,date1, date2);
    }

    /*
    @GetMapping("/businessid/{id}/{date}")
    public Flux<ClinicAppointment> findByBusinessidAndDate(
            @PathVariable("id") String id,
            @PathVariable("date") Long dt){
        log.info(" get appt called:"+ dt);
        return appointmentService.findByBusinessidAndDate(id,dt);
    }
*/
    @GetMapping("/familyid/{id}/{date}")
    public Flux<ClinicAppointment> findByFamilyidOrderByDateDesc(
            @PathVariable("id") String id,
            @PathVariable("date") Date dt ){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        Date date1 = cal.getTime();
        return appointmentService.findByFamilyidOrderByDateDesc(id,date1);
    }

    @GetMapping("/report/day/{businessid}/{date1}/{date2}")
    public Flux<ClinicAppointment> findDayReport(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date2){
        return appointmentService.findDayReport(id,date1, date2);
    }

    @GetMapping("/report/month/{businessid}/{date1}/{date2}")
    public Flux<ClinicAppointment> findMonthReport(
            @PathVariable("businessid") String id,
            @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  Date date1,
            @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  Date date2){

        return appointmentService.findMonthReport(id,date1, date2);
    }
}
