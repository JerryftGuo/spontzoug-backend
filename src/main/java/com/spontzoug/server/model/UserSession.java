package com.spontzoug.server.model;

import com.spontzoug.server.util.UserSessionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSession {
    private String type;
    private UserSessionUtil data;
    private String sign;
}
