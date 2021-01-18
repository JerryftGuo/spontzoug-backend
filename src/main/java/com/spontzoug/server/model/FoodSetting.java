package com.spontzoug.server.model;

import com.spontzoug.server.util.DeliveryOption;
import com.spontzoug.server.util.PaymentOption;
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
public class FoodSetting {
    @Id
    private String id;

    private Boolean online;
    private Boolean closed;
    private Boolean enabled;
    private Boolean orderable;
    private DeliveryOption deliveryoption;
    private PaymentOption paymentoption;
    private TaxRateOption taxrateoption;
    private BigDecimal deliveryfee;
    private BigDecimal  minimumorder;
    private String businessid;
    private String region;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
}