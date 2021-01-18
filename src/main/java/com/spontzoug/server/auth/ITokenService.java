package com.spontzoug.server.auth;

public interface ITokenService {
    String  generate(String prefix, String id);
    String  code(String id);
}