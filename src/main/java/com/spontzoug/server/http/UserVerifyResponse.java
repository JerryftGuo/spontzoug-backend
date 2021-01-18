package com.spontzoug.server.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVerifyResponse {
    private Boolean success;
    private Boolean userexist;
    private Boolean emailexist;
    private String token;
    private String message;
}