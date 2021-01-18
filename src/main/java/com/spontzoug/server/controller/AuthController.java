package com.spontzoug.server.controller;

import com.spontzoug.server.auth.IAuthService;
import com.spontzoug.server.auth.IPasswordService;
import com.spontzoug.server.http.*;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.PaymentMethod;
import com.spontzoug.server.auth.IAuthService;
import com.spontzoug.server.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;
    @Autowired
    private IPasswordService passwordService;

    @PostMapping("/user/signin")
    @ResponseStatus(HttpStatus.OK)
    public Mono<UserSignInResponse> usersignin(
            @RequestBody UserSignInRequest request,
            @RequestHeader HttpHeaders headers) {
        return authService.usersignin(request, headers);
    }

    @PostMapping("/user/codeverify")
    @ResponseStatus(HttpStatus.OK)
    public Mono<UserSignInResponse> usercodeverify(
            @RequestBody SignInVerifyRequest request,
            @RequestHeader HttpHeaders headers) {
        return authService.usercodeverify(request, headers);
    }
    @PostMapping("/user/signout")
    @ResponseStatus(HttpStatus.OK)
    public Mono<SignOutResponse> usersignout(
            @RequestBody SignOutRequest request) {
        return authService.usersignout(request);
    }


    @PostMapping("/customer/signin")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CustomerSignInResponse> customersignin(
            @RequestBody CustomerSignInRequest request,
            @RequestHeader HttpHeaders headers) {
        return authService.customersignin(request, headers);
    }

    @PostMapping("/customer/codeverify")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CustomerSignInResponse> customercodeverify(
            @RequestBody SignInVerifyRequest request,
            @RequestHeader HttpHeaders headers) {
        return authService.customercodeverify(request, headers);
    }
    @PostMapping("/customer/signout")
    @ResponseStatus(HttpStatus.OK)
    public Mono<SignOutResponse> customersignout(
            @RequestBody SignOutRequest request) {
        return authService.customersignout(request);
    }

    @PutMapping("/user/changepassword")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PasswordChangeResponse> userpasswordChange(
            @RequestBody PasswordChangeRequest request) {
        return passwordService.userUpdatePassword(request);
    }

    @PutMapping("/user/verifypasswordemail")
    @ResponseStatus(HttpStatus.OK)
    public Mono<UserVerifyResponse> userverifyEmail(
            @RequestBody UserVerifyRequest request,
            @RequestHeader HttpHeaders headers) {
        return passwordService.userVerifyEmail(request, headers);
    }

    @PutMapping("/user/resetpassword")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PasswordResetResponse> userresetPassword(
            @RequestBody PasswordResetRequest request,
            @RequestHeader HttpHeaders headers) {
        return passwordService.userResetPassword(request, headers);
    }

    @PutMapping("/customer/changepassword")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PasswordChangeResponse> customerPasswordChange(
            @RequestBody PasswordChangeRequest request) {
        return passwordService.customerUpdatePassword(request);
    }

    @PutMapping("/customer/verifypasswordemail")
    @ResponseStatus(HttpStatus.OK)
    public Mono<UserVerifyResponse> verifyEmail(
            @RequestBody UserVerifyRequest request,
            @RequestHeader HttpHeaders headers) {
        return passwordService.customerVerifyEmail(request, headers);
    }

    @PutMapping("/customer/resetpassword")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PasswordResetResponse> resetPassword(
            @RequestBody PasswordResetRequest request,
            @RequestHeader HttpHeaders headers) {
        return passwordService.customerResetPassword(request, headers);
    }

    @GetMapping("/hello")
    public Mono<String> hello(){
        return Mono.just("Hello world");
    }

}
