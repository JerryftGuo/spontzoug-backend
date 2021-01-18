package com.spontzoug.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    @Id
    private String id;
    private String region;
    private String industry;
    private String subtype;
    private String clientid;
    private String clientsignerid;
    private String businessid;
    private String planid;
    private String serial;
    private Boolean clientsigned;
    private Boolean platformsigned;
    private Boolean released;
    private String data;
    private String paperuri;
    private String signatureuri;
    private Date start;
    private Date end;
    private String clientsigner;
    private String clientsignedby;
    private String platformsignedby;
    private Date clientsignedon;
    private Date platformsignedon;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
    private String creator;
}
