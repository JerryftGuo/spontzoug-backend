package com.spontzoug.server.model;

import com.spontzoug.server.util.Address;
import com.spontzoug.server.util.WeekHours;
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

@Document(collection = "location")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    private String id;
    @Indexed
    private String  businessid;
    private String name;
    private String phone;
    private Address address;
    private List<WeekHours> hours;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
    private String creator;
   }
