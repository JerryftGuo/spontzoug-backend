package com.spontzoug.server.model;

import com.spontzoug.server.util.ImageItem;
import com.spontzoug.server.util.WeekHours;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    @Id
    private String id;
    private String title;
    private String Detail;
    private List<ImageItem> images;
    private List<WeekHours> hours;
    private String businessid;
    private String region;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
    private String creator;
}