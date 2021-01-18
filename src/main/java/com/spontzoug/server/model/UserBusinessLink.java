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
public class UserBusinessLink {
    @Id
    private String id;
    @Indexed
    private String userid;
    private String industry;
    private String subtype;
    private String region;
    private BusinessRole role;
}