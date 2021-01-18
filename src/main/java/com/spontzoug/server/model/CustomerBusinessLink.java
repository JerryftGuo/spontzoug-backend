package com.spontzoug.server.model;

import com.spontzoug.server.util.BusinessRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBusinessLink {
    @Id
    private String id;
    @Indexed
    private String customerid;
    private String industry;
    private String subtype;
    private String region;
    private String businessid;
}