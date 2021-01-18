package com.spontzoug.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetailerOrder {
    @Id
    private String id;
    private String type;
    @Indexed
    private String serial;
    private String confirmation;
    @Indexed
    private String businessid;
    @Indexed
    private String clientid;
    private String region;
    private Integer items;
    private BigDecimal amount;
    private String status;
    private String data;
    private Date pickup;
    private Date delivery;
    private String deliverer;
    private String cashier;
    private String paidtype;
    private BigDecimal paidamount;
    private Date paid;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
}