package com.spontzoug.server.controller;

import com.spontzoug.server.http.ClosingRequest;
import com.spontzoug.server.http.ClosingResponse;
import com.spontzoug.server.model.RegionCity;
import com.spontzoug.server.redis.ISessionService;
import com.spontzoug.server.service.IRegionCityService;
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
@RequestMapping("/api/regioncity")
public class RegionCityController {
    @Autowired
    private IRegionCityService regionCityService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody RegionCity l) {  regionCityService.create(l); }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<RegionCity> update(@RequestBody RegionCity m) {
        return regionCityService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return regionCityService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<RegionCity>> findById(@PathVariable("id") String id) {
        Mono<RegionCity> m = regionCityService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<RegionCity>>(m,s);
    }

    @GetMapping("/all")
    public Flux<RegionCity> findAll(){
        return regionCityService.findAll();
    }

    @GetMapping("/all/{date}")
    public Flux<RegionCity> findAllInc(
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return regionCityService.findAllInc(date);
    }

}
