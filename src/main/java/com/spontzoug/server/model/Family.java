package com.spontzoug.server.model;

import com.spontzoug.server.util.Address;
import com.spontzoug.server.util.Patient;
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

@Document(collection = "family")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Family {
    @Id
    private String id;
    private String phone;
    @Indexed
    private String businessid;
    private Address address;
    private List<Patient> members;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
    private String  creator;
}