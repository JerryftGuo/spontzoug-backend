package com.spontzoug.server.model;

import com.spontzoug.server.util.TaxRateOption;
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

public class SysPromotion {
    @Id
    private String id;
    private String businessid;
    private String name;
    private Boolean viponly;
    private BigDecimal threshold;
    private BigDecimal discount;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
}