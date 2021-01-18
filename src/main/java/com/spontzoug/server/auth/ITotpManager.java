package com.spontzoug.server.auth;

public interface  ITotpManager{
    String generateSecret();
    boolean validateCode(String code, String secret);
}