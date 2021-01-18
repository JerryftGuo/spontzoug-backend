package com.spontzoug.server.service;


import com.spontzoug.server.model.SystemSignature;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface ISystemSignatureService {
    void create (final String name,
                 final String businessid,
                 final MultipartFile file) throws IOException;
    void create (final String name,
                 final String basename,
                 final String businessid,
                 final FilePart file) throws IOException;
    Mono<SystemSignature> findById(final String id);
    Mono<SystemSignature> update(SystemSignature service);
    Mono<Void> deleteById(final String id);
    Mono<SystemSignature> findByName(final String name);
    Flux<SystemSignature> findByBaseName(final String name);
}