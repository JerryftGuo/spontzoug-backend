package com.spontzoug.server.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInVerifyRequest{
    private String token;
    private String code;
    private String ip;
    private String mac;
    private String device;
}