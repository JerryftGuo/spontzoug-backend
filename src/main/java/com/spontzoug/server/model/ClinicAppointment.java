package com.spontzoug.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection="clinicappointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@CompoundIndexes({
        @CompoundIndex(name="business_date", def="{'businessid':1, 'datetime':1 }"),
        @CompoundIndex(name="business_family", def="{'businessid':1, 'familyid':1}"),
})
public class ClinicAppointment {
    @Id
    private String id;
    private String client;
    private String practitioner;
    private String familyid;
    private String memberid;
    private String region;
    private String service;
    private String duration;
    private String room;
    private String businessid;
    private String location;
    private BigDecimal price;
    private String note;
    private Date start;
    private Date end;
    //    private Long date;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
    private String creator;

}