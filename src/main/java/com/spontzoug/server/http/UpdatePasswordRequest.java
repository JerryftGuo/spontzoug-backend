package com.spontzoug.server.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequest {
    private String username;
    private String oldpassword;
    private String newpassword;
}