package com.spontzoug.server.model;

import com.spontzoug.server.util.TaxRateOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TaxRate {
    @Id
    private String id;
    private String province;
    private TaxRateOption taxrateoption;
    private String businessid;
    private String creator;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
}