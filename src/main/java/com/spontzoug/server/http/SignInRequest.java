package com.spontzoug.server.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest{
    private String username;
    private String password;
    private String ip;
    private String mac;
    private String device;
}