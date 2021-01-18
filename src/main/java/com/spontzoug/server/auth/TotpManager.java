package com.spontzoug.server.auth;

import org.springframework.stereotype.Component;

@Component
public class TotpManager implements ITotpManager {
    public String generateSecret(){
        return new String();
    }
    public boolean validateCode(String code, String secret){
        return true;
    }
}