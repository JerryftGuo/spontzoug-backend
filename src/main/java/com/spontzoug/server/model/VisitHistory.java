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
public class VisitHistory {
    @Id
    private  String id;
    private  String businessid;
    @Indexed
    private String  familyid;
    private String  memberid;
    private String  practitioner;
    private String  symptoms;
    private String  treatment;
    private String  medicine;
    private List<ImageItem> images;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;

}