package com.spontzoug.server.model;

import com.spontzoug.server.util.TemporarySessionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemporarySession {
    private TemporarySessionUtil data;
    private String sign;
}
