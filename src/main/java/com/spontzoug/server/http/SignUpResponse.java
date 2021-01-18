package com.spontzoug.server.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponse {
    private Boolean success;
    private Boolean exist;
    private String uid;
    private String username;
    private String token;
    private String message;
}