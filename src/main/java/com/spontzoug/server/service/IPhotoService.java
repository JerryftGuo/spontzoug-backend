package com.spontzoug.server.service;


import com.spontzoug.server.model.Photo;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface IPhotoService {
    void create (final String name,final String businessid, final MultipartFile file) throws IOException;
    void create (final String name, final String basename, final String businessid, final FilePart file) throws IOException;
    Mono<Photo> findById(final String id);
    Mono<Photo> update(Photo service);
    Mono<Void> deleteById(final String id);
    Mono<Photo> findByName(final String name);
    Flux<Photo> findByBaseName(final String name);
}