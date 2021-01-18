package com.spontzoug.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public  class ProcessingSysReport{
    @Id
    private String id;
    private String region;
    private String industry;
    private Date created;
    private Date modified;
    private Integer items;
    private BigDecimal amount;
}