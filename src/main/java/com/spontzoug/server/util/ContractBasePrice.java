package com.spontzoug.server.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class ContractBasePrice {
    private BigDecimal price;
    private BigDecimal promotion;
    private BigDecimal extradiscount;
}