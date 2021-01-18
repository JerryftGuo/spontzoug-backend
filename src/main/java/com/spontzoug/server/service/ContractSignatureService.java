package com.spontzoug.server.service;

import com.spontzoug.server.model.ContractSignature;
import com.spontzoug.server.repository.IContractSignatureRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.ByteBuf;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.ByteBuffer;

@Slf4j
@Service
public class ContractSignatureService implements IContractSignatureService {
    @Autowired
    private IContractSignatureRepository contractsignatureRepository;

    public void create(final String name, final String businessid, MultipartFile file) throws IOException {
        ContractSignature p = new ContractSignature();

        Binary image = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        p.setName(name);
        p.setClientid(businessid);
        p.setSignature(image);

        contractsignatureRepository.save(p).subscribe();
    }

    public void create(final String name, final String businessid, FilePart file) throws IOException {
        file.content().collectList().subscribe(
                listData ->{
                    log.info("list.size:"+listData.size());
                    int totalSize =0;
                    for ( DataBuffer data: listData
                    ) {
                        totalSize += data.readableByteCount();
                        log.info("data length"+ data.readableByteCount());
                    }
                    log.info("total length"+ totalSize);

                    byte[] buffer = new byte[totalSize];
                    int offset =0;
                    for ( DataBuffer data: listData
                    ) {
                        for(int i=0; i<data.readableByteCount(); i++){
                            buffer[offset+i] = data.getByte(i);
                        }
                        offset += data.readableByteCount();
                    }

                    ContractSignature p = new ContractSignature();
                    Binary image = new Binary(BsonBinarySubType.BINARY, buffer);
                    p.setName(name);
                    p.setClientid(businessid);
                    p.setSize(totalSize);
                    p.setSignature(image);
                    contractsignatureRepository.save(p).subscribe();


                }
        );
    }

    public Mono<ContractSignature> update(ContractSignature contractsignature){
        return contractsignatureRepository.save(contractsignature);
    }

    public Mono<Void> deleteById(final String id){
        return contractsignatureRepository.deleteById(id);
    }

    public Mono<ContractSignature> findById(final String id){
        return contractsignatureRepository.findById(id);
    }

    public Mono<ContractSignature> findByName(final String name) {
        return contractsignatureRepository.findByName(name);
    }
}