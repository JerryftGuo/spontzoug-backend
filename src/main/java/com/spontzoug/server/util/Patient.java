package com.spontzoug.server.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class  Patient {
    private Long memberid;
    private String gender;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
     private Date dateofbirth;
    private Boolean primary;
}