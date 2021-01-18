package com.spontzoug.server.model;

import com.spontzoug.server.util.*;
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

@Document(collection="practitioner")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Practitioner {
    @Id
    private String id;
    private String userid;
    @Indexed
    private String businessid;
    private String region;
    private String title;
    private String firstname;
    private String lastname;
    private String certificateno;
    private String phone;
    private String email;
    private String description;
    private String invitation;
    private Address address;
    private List<WeekHours> hours;
    private Dates vacation;
    private List<ImageItem> images;
    private Boolean closed;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
    private String creator;
}