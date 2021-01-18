package com.spontzoug.server.auth;

public interface IVerifyCodeService {
    String generate32();
    String generate64();
}