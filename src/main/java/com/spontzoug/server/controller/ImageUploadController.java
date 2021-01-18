package com.spontzoug.server.controller;

import com.spontzoug.server.service.IImageUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/upload")
public class ImageUploadController {
    @Autowired
    private IImageUploadService imageUploadService;

    @PostMapping(value="/image/")
   //         consumes= MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void imageUpload(
   //         @PathVariable("province") String province,
   //         @PathVariable("city") String city,
   //         @PathVariable("industry") String industry,
    //    @RequestParam("filename") String filename,
           @RequestPart("file") MultipartFile filePart
    ){

    //    log.info("upload saved"+filename);
        log.info("file:"+filePart);

//        return Mono.empty();
        imageUploadService.save("ab","clagary","helath",filePart);
    }

}