package com.spontzoug.server.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysReportBillingResponse {
    private String id;
    private String industry;
    private Date billing;
    private Date created;
    private BigDecimal subtotal;
    private BigDecimal promotion;
    private BigDecimal gst;
    private BigDecimal pst;
    private BigDecimal hst;
    private BigDecimal total;
    private BigDecimal paidamount;
}