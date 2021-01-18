package com.spontzoug.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Config {
    @Id
    private String id;
    private String type;
    private String name;
    private String version;
    private String release;
    private String data;
    private Date created;
    private Date modified;
}