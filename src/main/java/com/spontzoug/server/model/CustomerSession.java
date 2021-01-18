package com.spontzoug.server.model;

import com.spontzoug.server.util.CustomerSessionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSession {
    private String type;
    private CustomerSessionUtil data;
    private String sign;
}
