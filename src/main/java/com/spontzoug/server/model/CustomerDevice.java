package com.spontzoug.server.model;

import com.spontzoug.server.util.BusinessRole;
import com.spontzoug.server.util.DeviceInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDevice {
    @Id
    private String id;
    @Indexed
    private String customerid;
    private DeviceInfo device;
}