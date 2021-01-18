package com.spontzoug.server.controller;

import com.spontzoug.server.model.SysSetting;
import com.spontzoug.server.model.SystemSignature;
import com.spontzoug.server.service.ISystemSignatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/systemsignature")
public class SystemSignatureController {
    @Autowired
    private ISystemSignatureService photoService;

    @PostMapping(value = "/create/{businessid}/{basename}/{name}",
            consumes =  MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create (
            @PathVariable("businessid") String businessid,
            @PathVariable("basename") String basename,
            @PathVariable("name") String name,
            @RequestPart("file") FilePart filePart) throws IOException {
        log.info("put photo name:" + name + " id:" + businessid + " file:" + filePart);
        photoService.findByName(name)
       .flatMap( ele -> {
                   return photoService.deleteById(ele.getId());
        }).subscribe();
        photoService.create(name, basename, businessid,filePart);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<SystemSignature> update(@RequestBody SystemSignature m) {
        return photoService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return photoService.deleteById(id);
    }

    @DeleteMapping("/delete/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteByName (@PathVariable("name") String name) {
        return photoService.findByName(name)
                .flatMap( img ->{
                    return photoService.deleteById(img.getId());
                }).switchIfEmpty(
                        Mono.empty()
                );
    }

    @GetMapping(value="/name/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<byte[]> findByName(@PathVariable("name") String name ) {
        Mono<SystemSignature> photo = photoService.findByName(name);
        Mono<byte[]> ph = photo.map(p ->
                p.getSignature().getData()
        );
        return ph;
    }

    @GetMapping("/basename/{name}")
    public Flux<SystemSignature> findByBaseName(@PathVariable("name") String name){
        return photoService.findByBaseName(name);
    }

}

