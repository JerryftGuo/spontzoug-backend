package com.spontzoug.server.controller;

import com.spontzoug.server.annotation.IsUserAuthenticationEntAdmin;
import com.spontzoug.server.model.ContractTemplate;
import com.spontzoug.server.service.IContractTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/contracttemplate")
public class ContractTemplateController {
    @Autowired
    private IContractTemplateService contracttemplateService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ContractTemplate l) {
        contracttemplateService.create(l);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ContractTemplate> update(@RequestBody ContractTemplate m) {
        return contracttemplateService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return contracttemplateService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<ContractTemplate>> findById(@PathVariable("id") String id) {
        Mono<ContractTemplate> m = contracttemplateService.findById(id);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<ContractTemplate>>(m,s);
    }

    @GetMapping("/prod/{name}")
    @IsUserAuthenticationEntAdmin
    public Mono<ContractTemplate> findProductByName(@PathVariable("name") String name){
        return contracttemplateService.findProductByName(name);
    }

    @GetMapping("/prod/inc/{name}/{date}")
    public Mono<ContractTemplate> findProductIncByName(
            @PathVariable("name") String name,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return contracttemplateService.findProductIncByName(name, date);
    }

    @GetMapping("/all")
    public Flux<ContractTemplate> findProductAll(){
        return contracttemplateService.findProductAll();
    }

    @GetMapping("/all/{date}")
    public Flux<ContractTemplate> findProductIncAll(
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date){
        return contracttemplateService.findProductIncAll(date);
    }
}
