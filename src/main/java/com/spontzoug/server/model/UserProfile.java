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

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    @Id
    private String id;
    @Indexed
    private String userid;
    private String title;
    private String firstname;
    private String lastname;
    private String cellphone;
    private String email;
    private Address address;
    private List<ImageItem> images;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
}