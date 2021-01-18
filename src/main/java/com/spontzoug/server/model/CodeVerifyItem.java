package com.spontzoug.server.model;

import com.spontzoug.server.util.CodeVerifyUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeVerifyItem {
    private CodeVerifyUtil data;
    private String sign;
}