package com.spontzoug.server.controller;

import com.spontzoug.server.model.ContractSignature;
import com.spontzoug.server.service.IContractSignatureService;
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
@RequestMapping("/api/contractsignature")
public class ContractSignatureController {
    @Autowired
    private IContractSignatureService contractsignatureService;

    @PostMapping(value = "/create/{businessid}/{name}",
            consumes =  MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create (

            @PathVariable("businessid") String businessid,
            @PathVariable("name") String name,
            @RequestPart("file") FilePart filePart) throws IOException {
        log.info("put contractsignature name:" + name + " id:" + businessid + " file:" + filePart);
        contractsignatureService.create(name, businessid,filePart);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ContractSignature> update(@RequestBody ContractSignature m) {
        return contractsignatureService.update(m);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById (@PathVariable("id") String id) {
        return contractsignatureService.deleteById(id);
    }

    @DeleteMapping("/delete/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteByName (@PathVariable("name") String name) {
        return contractsignatureService.findByName(name)
                .flatMap( img ->{
                    return contractsignatureService.deleteById(img.getId());
                }).switchIfEmpty(
                        Mono.empty()
                );
    }

    @GetMapping(value="/name/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<byte[]> findByName(@PathVariable("name") String name ) {
        log.info("get iamge called "+ name);

        Mono<ContractSignature> contractsignature = contractsignatureService.findByName(name);
        Mono<byte[]> ph = contractsignature.map(p ->
                p.getSignature().getData()
        );
        return ph;
    }
}
