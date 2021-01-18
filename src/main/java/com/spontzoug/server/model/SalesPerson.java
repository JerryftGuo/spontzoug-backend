package com.spontzoug.server.model;

import com.spontzoug.server.util.Address;
import com.spontzoug.server.util.Dates;
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
public class SalesPerson {
    @Id
    private String id;
    private String userid;
    @Indexed
    private String businessid;
    private String firstname;
    private String lastname;
    private String phone;
    private Address address;
    private String invitation;
    private String referencecode;
    private Dates vacation;
    private List<ImageItem> images;
    private Boolean closed;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
    private String Creator;

}