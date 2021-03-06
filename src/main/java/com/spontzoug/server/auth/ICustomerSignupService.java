package com.spontzoug.server.auth;

import com.spontzoug.server.http.*;
import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.Config;
import com.spontzoug.server.model.PaymentMethod;
import com.spontzoug.server.model.UserProfile;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface  ICustomerSignupService {
    Mono<UserVerifyResponse> userverify(UserVerifyRequest request, HttpHeaders header);
    Mono<CodeVerifyResponse> codeverify(CodeVerifyRequest request, HttpHeaders header);
    Mono<SignUpResponse> usersignup(SignUpRequest request, HttpHeaders header);
    Mono<FinishSignupResponse> finish (FinishSignupRequest request);
}