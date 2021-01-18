package com.spontzoug.server.model;

import com.spontzoug.server.util.ImageItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Document(collection = "service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @Id
    private String id;
    private String name;
    private String color;
    private Boolean taxable;
    private String description;
    private String duration;
    private BigDecimal price;
    private String businessid;
    private String region;
    private List<ImageItem> images;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
    private String creator;
}