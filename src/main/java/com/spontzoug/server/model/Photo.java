package com.spontzoug.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
    @Id
    private String id;
    @Indexed
    private String name;
    private String basename;
    private String businessid;
    private Integer size;
    private Binary image;
}
