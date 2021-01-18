package com.spontzoug.server.model;

import com.spontzoug.server.util.ContractBaseLimit;
import com.spontzoug.server.util.ContractBasePrice;
import com.spontzoug.server.util.ContractOverlimitPrice;
import com.spontzoug.server.util.ContractProcessPrice;
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
public class PlanOption {
    @Id
    private String id;
    private Boolean valid;
    private String name;
    private String industry;
    private String subtype;
    private String businessid;
    private Boolean usingannualprice;
    private ContractBasePrice annualprice;
    private ContractBasePrice monthprice;
    private ContractBaseLimit baselimit;
    private ContractOverlimitPrice overlimitprice;
    private ContractProcessPrice processprice;
    private String billingordertablename;
    private String billingappointmenttablename;
    private String billingitemtablename;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
    private String Creator;
}