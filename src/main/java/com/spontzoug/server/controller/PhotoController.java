package com.spontzoug.server.controller;

import com.spontzoug.server.model.Photo;
import com.spontzoug.server.service.IPhotoService;
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
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private IPhotoService photoService;

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
        photoService.findByBaseName(basename)
                .flatMap( ele -> {
                    return photoService.deleteById(ele.getId());
                }).subscribe();
        photoService.create(name, basename, businessid,filePart);
    }
    /*
    @PostMapping(value = "/create/{businessid}/{name}",
            consumes =  MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create (
            @RequestHeader Map<String, String> headers,
            @PathVariable("businessid") String businessid,
            @PathVariable("name") String name,
            @RequestPart("file") MultipartFile filePart) throws IOException {
   //         @RequestPart(value="file") Flux<MultipartFile> filePart) throws IOException {
        log.info("put photo name:"+name+" id:"+businessid+" file:"+filePart);
        log.info("put file:"+ filePart);
        headers.forEach((key,value) -> log.info("key:"+ key + " value:"+ value));
    //    photoService.create(name, businessid,filePart);
    }
*/
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Photo> update(@RequestBody Photo m) {
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
        log.info("get iamge called "+ name);

        Mono<Photo> photo = photoService.findByName(name);
        Mono<byte[]> ph = photo.map(p ->
               p.getImage().getData()
           );
        return ph;
        }
    /*
    @GetMapping(value="/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Mono<Photo>> findById(@PathVariable("name") String name) {
        log.info("get image called:"+ name);
        Mono<Photo> m = photoService.findByName(name);
        HttpStatus s = m!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Photo>>(m,s);
    }
     */
}

    /*
       public  ResponseEntity<Mono<Photo>> findByName(@PathVariable("name") String name, ServerHttpResponse response) {
           log.info("get iamge called "+ name);

           Mono<Photo> photo = photoService.findByName(name);
           HttpStatus s = photo != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
           /*
           Mono<byte[]> ph = photo.map( p ->
               p.getImage().getData()
           );

    byte[] b = new byte[10];
    Mono<byte[]> mb = Mono.just(b);
        return new ResponseEntity<Mono<Photo>> (photo, s);
        }
        */