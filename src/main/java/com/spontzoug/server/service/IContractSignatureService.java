package com.spontzoug.server.service;


import com.spontzoug.server.model.ContractSignature;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface IContractSignatureService {
    void create (final String name, final String businessid, final MultipartFile file) throws IOException;
    void create (final String name, final String businessid, final FilePart file) throws IOException;
    Mono<ContractSignature> findById(final String id);
    Mono<ContractSignature> update(ContractSignature service);
    Mono<Void> deleteById(final String id);
    Mono<ContractSignature> findByName(final String name);
}