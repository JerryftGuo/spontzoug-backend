package com.spontzoug.server.model;

import com.spontzoug.server.util.Address;
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
public class Billing {
    @Id
    private String id;
    private String serial;
    private String businessid;
    private String issuerid;
    private String industry;
    private String region;
    private BigDecimal subtotal;
    private BigDecimal promotion;
    private BigDecimal gst;
    private BigDecimal pst;
    private BigDecimal hst;
    private BigDecimal total;
    private String data;
    private String paidtype;
    private BigDecimal chargedamount;
    private BigDecimal paidamount;
    private String authoritycode;
    private Date billing;
    private Date paid;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
    private String creator;
}