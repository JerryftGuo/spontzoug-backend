package com.spontzoug.server.controller;

import com.spontzoug.server.model.Invoice;
import com.spontzoug.server.service.IInvoiceService;
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
@RequestMapping("/api/invoice")
public class InvoiceController {
    @Autowired
    private IInvoiceService invoiceService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Invoice l) {
        invoiceService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Invoice> update(@RequestBody Invoice m) {
        log.info("put appt");
        return invoiceService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById(@PathVariable("id") String id) {
        log.info("del appt");
        return invoiceService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<Invoice>> findById(@PathVariable("id") String id) {
        Mono<Invoice> m = invoiceService.findById(id);
        HttpStatus s = m != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Invoice>>(m, s);
    }

    @GetMapping("/businessid/{id}/{date}")
    public Flux<Invoice> findByBusinessidAndDate(
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
        return invoiceService.findByBusinessidAndDate(id, date1, date2);
    }

    @GetMapping("/clientid/{id}/{date}")
    public Flux<Invoice> findByClientidAndDate(
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
        return invoiceService.findByClientidAndDate(id, date1, date2);
    }

    @GetMapping("/appontmentid/{id}")
    public ResponseEntity<Mono<Invoice>> findByAppointmentId(@PathVariable("id") String id) {
        Mono<Invoice> m = invoiceService.findByAppointmentid(id);
        HttpStatus s = m != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Invoice>>(m, s);
    }
}
