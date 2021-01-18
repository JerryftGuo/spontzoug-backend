package com.spontzoug.server.http;

import com.spontzoug.server.util.BusinessRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSignInResponse {
    private Boolean success;
    private Boolean duplicate;
    private Boolean verifyrequired;
    private Integer tried;
    private Integer limit;
    private String uid;
    private String region;
    private String role;
    private String token;
    private String message;
}