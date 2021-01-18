package com.spontzoug.server.auth;

import com.spontzoug.server.http.*;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

public interface  IPasswordService {
    Mono<PasswordChangeResponse> userUpdatePassword(PasswordChangeRequest request);
    Mono<UserVerifyResponse> userVerifyEmail(UserVerifyRequest request, HttpHeaders headers);
    Mono<PasswordResetResponse> userResetPassword(PasswordResetRequest resetRequest, HttpHeaders headers);
    Mono<PasswordChangeResponse> customerUpdatePassword(PasswordChangeRequest request);
    Mono<UserVerifyResponse> customerVerifyEmail(UserVerifyRequest request, HttpHeaders headers);
    Mono<PasswordResetResponse> customerResetPassword(PasswordResetRequest resetRequest, HttpHeaders headers);
}