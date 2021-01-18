package com.spontzoug.server.http;

import com.spontzoug.server.model.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileVerifyRequest {
    private String token;
    private String code;
    private UserProfile profile;
}