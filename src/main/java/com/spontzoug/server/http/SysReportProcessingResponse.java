package com.spontzoug.server.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysReportProcessingResponse {
    private String id;
    private String industry;
    private Date created;
    private Integer items;
    private BigDecimal amount;
}