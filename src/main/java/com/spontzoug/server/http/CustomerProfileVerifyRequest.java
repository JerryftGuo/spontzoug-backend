package com.spontzoug.server.http;

import com.spontzoug.server.model.CustomerProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfileVerifyRequest {
    private String token;
    private String code;
    private CustomerProfile profile;
}