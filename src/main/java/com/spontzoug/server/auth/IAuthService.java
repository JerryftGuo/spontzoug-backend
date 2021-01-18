package com.spontzoug.server.auth;

import com.spontzoug.server.http.*;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;



public interface  IAuthService {
    Mono<UserSignInResponse> usersignin(UserSignInRequest request, HttpHeaders headers);
    Mono<UserSignInResponse> usercodeverify(SignInVerifyRequest request, HttpHeaders headers);
    Mono<SignOutResponse> usersignout(SignOutRequest request);

    Mono<CustomerSignInResponse> customersignin(CustomerSignInRequest request, HttpHeaders headers);
    Mono<CustomerSignInResponse> customercodeverify(SignInVerifyRequest request, HttpHeaders headers);
    Mono<SignOutResponse> customersignout(SignOutRequest request);

}