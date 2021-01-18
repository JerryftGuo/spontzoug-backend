package com.spontzoug.server.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSessionUtil {
    private String uid;
    private String username;
    private String role;
    private String businessid;
    private String ipaddress;
    private String device;
    private Date created;
    private Date lastestaccess;
}