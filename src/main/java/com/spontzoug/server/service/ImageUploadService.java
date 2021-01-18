package com.spontzoug.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

@Slf4j
@Service
public class ImageUploadService implements IImageUploadService {
    @Value("${image.path}")
    private String imagePath;

    public void save(
            String province,
            String city,
            String industry,
            MultipartFile filePart){
        String dir = imagePath +province + "/"+ city +"/"+industry;
        FileSystemResource res = new FileSystemResource(dir);
        Path realPath = Paths.get(res.getPath());

        try(OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(realPath,CREATE, APPEND)))
        {
                byte data[] = filePart.getBytes();
                out.write(data, 0, data.length);
        } catch ( IOException e) {
            log.info(" file :" + e.getMessage());
        } finally {
        }
    }
}


/*
   AsynchronousFileChannel channel =
            AsynchronousFileChannel.open(filePath, StandardOpenOption.WRITE,StandardOpenOption.TRUNCATE_EXISTING)) {
                   DataBufferUtils.write(filePart.content(), channel, 0)
                    .doOnComplete(() -> {
                    })
                    .subscribe();
*
*
 */