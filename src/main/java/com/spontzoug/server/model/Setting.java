package com.spontzoug.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Setting {
    @Id
    private String id;

    private Boolean online;
    private Boolean closed;
    private Boolean active;
    private Boolean bookable;
    private Boolean orderable;
    private Boolean pickup;
    private Boolean onsite;
    private Boolean delivery;
    private Boolean receivecredit;
    private Boolean receivedebit;
    private Boolean receivecash;

    private BigDecimal deliveryfee;
    private BigDecimal  minimumorder;

    private BigDecimal gst;
    private BigDecimal pst;
    private BigDecimal hst;

    private String businessid;
    private String region;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
}