package com.spontzoug.server.service;

import com.spontzoug.server.model.Photo;
import com.spontzoug.server.repository.IPhotoRepository;
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
public class PhotoService implements IPhotoService {
    @Autowired
    private IPhotoRepository photoRepository;

    public void create(final String name, final String businessid, MultipartFile file) throws IOException {
        Photo p = new Photo();

            Binary image = new Binary(BsonBinarySubType.BINARY, file.getBytes());
            p.setName(name);
            p.setBusinessid(businessid);
            p.setImage(image);

        photoRepository.save(p).subscribe();
    }

    public void create(
            final String name,
            final String basename,
            final String businessid,
            FilePart file) throws IOException {
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

                    Photo p = new Photo();
                    Binary image = new Binary(BsonBinarySubType.BINARY, buffer);
                    p.setName(name);
                    p.setBasename(basename);
                    p.setBusinessid(businessid);
                    p.setSize(totalSize);
                    p.setImage(image);
                    photoRepository.save(p).subscribe();


                }
        );
    }

    public Mono<Photo> update(Photo photo){
        return photoRepository.save(photo);
    }

    public Mono<Void> deleteById(final String id){
        return photoRepository.deleteById(id);
    }

    public Mono<Photo> findById(final String id){
        return photoRepository.findById(id);
    }

    public Mono<Photo> findByName(final String name) {
        return photoRepository.findByName(name);
    }
    public Flux<Photo> findByBaseName(final String name) {
        return photoRepository.findByBaseName(name);
    }
}