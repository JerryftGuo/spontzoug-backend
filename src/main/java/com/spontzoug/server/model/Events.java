package com.spontzoug.server.model;

import com.spontzoug.server.util.Address;
import com.spontzoug.server.util.ImageItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Events {
    @Id
    private String id;
    private String title;
    private Address address;
    private BigDecimal regularprice;
    private BigDecimal seniorprice;
    private Integer freeage;
    private String note;
    private Date dooropen;
    private Date start;
    private Date end;
    private List<ImageItem> images;
    private String businessid;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
    private String creator;

}