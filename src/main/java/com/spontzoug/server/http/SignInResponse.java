package com.spontzoug.server.http;

import com.spontzoug.server.util.BusinessRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponse {
    private Boolean success;
    private Boolean duplicate;
    private Boolean verifyrequired;
    private String uid;
    private String buinessid;
    private String businessname;
    private String region;
    private BusinessRole role;
    private String token;
}