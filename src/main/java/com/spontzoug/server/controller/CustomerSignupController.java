package com.spontzoug.server.controller;

import com.spontzoug.server.auth.ICustomerSignupService;
import com.spontzoug.server.http.*;

import com.spontzoug.server.model.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@RestController
@RequestMapping("/customersignup")
public class CustomerSignupController {
    @Autowired
    private ICustomerSignupService customerSignupService;

    @PostMapping("/userverify")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserVerifyResponse> userverify(
            @RequestBody UserVerifyRequest request,
            @RequestHeader HttpHeaders headers) {
        return customerSignupService.userverify(request, headers);
    }
    @PostMapping("/codeverify")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CodeVerifyResponse> codeverify(
            @RequestBody CodeVerifyRequest request,
            @RequestHeader HttpHeaders headers) {
        return customerSignupService.codeverify(request, headers);
    }


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<SignUpResponse> usersignup(@RequestBody SignUpRequest request, @RequestHeader HttpHeaders header) {
        return customerSignupService.usersignup(request, header);
    }



    @PostMapping("/finish")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FinishSignupResponse> signup(@RequestBody FinishSignupRequest request) {
        return customerSignupService.finish(request);
    }

}
