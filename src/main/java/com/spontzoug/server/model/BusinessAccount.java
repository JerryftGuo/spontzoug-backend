package com.spontzoug.server.model;

import com.spontzoug.server.util.Address;
import com.spontzoug.server.util.Dates;
import com.spontzoug.server.util.ImageItem;
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
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessAccount {
    @Id
    private String id;;
    @Indexed
    private String businessid;
    private String region;
    private String industry;
    private String subtype;
    private Boolean hascontract;
    private String contractid;
    private Date chargingstart;
    private Date contractstart;
    private Date contractend;
    private Boolean annualpaid;
    private Boolean isvip;
    private Boolean closed;
    private Integer billingday;
    private BigDecimal previousbalance;
    private BigDecimal balance;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
    private String creator;

}