package com.spontzoug.server.service;

import com.spontzoug.server.model.ContractPaper;
import com.spontzoug.server.repository.IContractPaperRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.ByteBuf;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.ByteBuffer;

@Slf4j
@Service
public class ContractPaperService implements IContractPaperService {
    @Autowired
    private IContractPaperRepository photoRepository;

    public void create(final String name, final String businessid, MultipartFile file) throws IOException {
        ContractPaper p = new ContractPaper();

        Binary image = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        p.setName(name);
        p.setClientid(businessid);
        p.setContract(image);

        photoRepository.save(p).subscribe();
    }

    public void create(final String name, final String businessid, FilePart file) throws IOException {
        file.content().collectList().subscribe(
                listData ->{
                  //  log.info("list.size:"+listData.size());
                    int totalSize =0;
                    for ( DataBuffer data: listData
                    ) {
                        totalSize += data.readableByteCount();
                        log.info("data length"+ data.readableByteCount());
                    }
                //    log.info("total length"+ totalSize);

                    byte[] buffer = new byte[totalSize];
                    int offset =0;
                    for ( DataBuffer data: listData
                    ) {
                        for(int i=0; i<data.readableByteCount(); i++){
                            buffer[offset+i] = data.getByte(i);
                        }
                        offset += data.readableByteCount();
                    }

                    ContractPaper p = new ContractPaper();
                    Binary image = new Binary(BsonBinarySubType.BINARY, buffer);
                    p.setName(name);
                    p.setClientid(businessid);
                    p.setSize(totalSize);
                    p.setContract(image);
                    photoRepository.save(p).subscribe();


                }
        );
    }

    public Mono<ContractPaper> update(ContractPaper photo){
        return photoRepository.save(photo);
    }

    public Mono<Void> deleteById(final String id){
        return photoRepository.deleteById(id);
    }

    public Mono<ContractPaper> findById(final String id){
        return photoRepository.findById(id);
    }

    public Mono<ContractPaper> findByName(final String name) {
        return photoRepository.findByName(name);
    }
}