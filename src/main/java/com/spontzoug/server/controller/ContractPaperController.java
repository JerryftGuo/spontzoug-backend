package com.spontzoug.server.controller;

import com.spontzoug.server.model.ContractPaper;
import com.spontzoug.server.service.IContractPaperService;
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
@RequestMapping("/contractpaper")
public class ContractPaperController {
    @Autowired
    private IContractPaperService photoService;

    @PostMapping(value = "/create/{businessid}/{name}",
            consumes =  MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create (
            @PathVariable("businessid") String businessid,
            @PathVariable("name") String name,
            @RequestPart("file") FilePart filePart) throws IOException {
        log.info("put contract name:" + name + " id:" + businessid + " file:" + filePart);
        photoService.create(name, businessid,filePart);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ContractPaper> update(@RequestBody ContractPaper m) {
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


    @GetMapping(value="/name/{name}", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<byte[]> findByName(@PathVariable("name") String name ) {
        Mono<ContractPaper> photo = photoService.findByName(name);
        Mono<byte[]> ph = photo.map(p ->
                p.getContract().getData()
        );
        return ph;
    }
}
