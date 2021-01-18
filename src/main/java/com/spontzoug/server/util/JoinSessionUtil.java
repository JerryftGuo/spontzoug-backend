package com.spontzoug.server.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinSessionUtil {
    private String businessid;
    private String staffid;
    private String role;
    private String username;
    private String email;
    private String ipaddress;
    private String code;
    private Date created;
}