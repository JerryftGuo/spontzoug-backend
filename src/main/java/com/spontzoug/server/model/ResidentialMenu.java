package com.spontzoug.server.model;

import com.spontzoug.server.util.*;
import com.spontzoug.server.util.Sale;
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
public class ResidentialMenu {
    @Id
    private String id;
    @Indexed
    private String businessid;
    private String region;
    private String category;
    private String brand;
    private String name;
    private String color;
    private String size;
    private BigDecimal baseprice;
    private List<ImageItem> images;
    private Sale onsale;
    private Boolean deleted;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
}