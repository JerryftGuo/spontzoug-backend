package com.spontzoug.server.service;


import com.spontzoug.server.model.ContractPaper;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface IContractPaperService {
    void create (final String name, final String businessid, final MultipartFile file) throws IOException;
    void create (final String name, final String businessid, final FilePart file) throws IOException;
    Mono<ContractPaper> findById(final String id);
    Mono<ContractPaper> update(ContractPaper service);
    Mono<Void> deleteById(final String id);
    Mono<ContractPaper> findByName(final String name);
}