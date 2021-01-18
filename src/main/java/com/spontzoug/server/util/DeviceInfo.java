package com.spontzoug.server.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public  class DeviceInfo{
    private String host;
    private String ip;
    private String mac;
    private String name;
    private Boolean current;
    private Date created;
}