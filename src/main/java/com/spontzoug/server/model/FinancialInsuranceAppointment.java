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

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialInsuranceAppointment {
    @Id
    private String id;
    private String client;
    private String financialinsurancestaff;
    private String region;
    private String service;
    private String duration;
    private String businessid;
    private String location;
    private BigDecimal price;
    private String note;
    private Date start;
    private Date end;
    private Boolean cancelled;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
    private String creator;

}