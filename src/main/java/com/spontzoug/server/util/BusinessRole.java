package com.spontzoug.server.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BusinessRole {
    private Boolean active;
    private String  businessid;
    private String  staffid;
    private String  businessname;
    private String  role;
}