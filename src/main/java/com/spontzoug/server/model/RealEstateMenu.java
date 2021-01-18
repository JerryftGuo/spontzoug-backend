package com.spontzoug.server.model;

import com.spontzoug.server.util.*;
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

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealEstateMenu {
    @Id
    private String id;
    private String businessid;
    private String region;
    private String mlsnumber;
    private String community;
    private String description;
    private Address address;
    private PropertySummary propertysummary;
    private BuildingFeature buildingfeature;
    private BuildingExterior buildingexterior;
    private BuildingInterior buildinginterior;
    private List<BuildingRoom> buildingrooms;
    private BigDecimal price;
    private List<ImageItem> images;

    private Boolean deleted;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
}