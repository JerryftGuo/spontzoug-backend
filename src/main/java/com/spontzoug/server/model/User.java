package com.spontzoug.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
    @Id
    private  String id;
    @TextIndexed
    private String username;
    private String email;
    private String password;
    private String status;
    private Boolean enabled;
    private Boolean locked;
    private Boolean signedin;
    private Integer tried;
    private String session;
    private String token;
    private Date locktime;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
}