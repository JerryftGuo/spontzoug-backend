package com.spontzoug.server.model;

import com.spontzoug.server.util.JoinSessionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinSession {
    private JoinSessionUtil data;
    private String sign;
}
