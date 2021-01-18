package com.spontzoug.server.model;

import com.spontzoug.server.util.ImageItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCate{
    @Id
    private String id;
    @Indexed
    private String businessid;
    private String region;
    private String name;
    private String color;
    private List<ImageItem> images;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
}