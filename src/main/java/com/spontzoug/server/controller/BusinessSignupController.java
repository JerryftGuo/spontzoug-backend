package com.spontzoug.server.controller;

import com.spontzoug.server.auth.IBusinessSignupService;
import com.spontzoug.server.http.*;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.Config;
import com.spontzoug.server.model.PaymentMethod;
import com.spontzoug.server.auth.IBusinessSignupService;
import com.spontzoug.server.annotation.*;

import com.spontzoug.server.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@RestController
@RequestMapping("/businesssignup")
public class BusinessSignupController {
    @Autowired
    private IBusinessSignupService businessSignupService;

    @PostMapping("/userverify")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserVerifyResponse> userverify(
            @RequestBody UserVerifyRequest request,
            @RequestHeader HttpHeaders headers) {
        return businessSignupService.userverify(request, headers);
    }
    @PostMapping("/codeverify")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CodeVerifyResponse> codeverify(
            @RequestBody CodeVerifyRequest request,
            @RequestHeader HttpHeaders headers) {
        return businessSignupService.codeverify(request, headers);
    }
    @PostMapping("/innercodeverify")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<InnerCodeVerifyResponse> innercodeverify(
            @RequestBody InnerCodeVerifyRequest request
            ) {
        return businessSignupService.innercodeverify(request);
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<SignUpResponse> usersignup(@RequestBody SignUpRequest request, @RequestHeader HttpHeaders header) {
        return businessSignupService.usersignup(request, header);
    }


    @PostMapping("/business")
    @IsUserAuthenticationEntSignup
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BusinessSignupResponse> businesssignup(
            @RequestParam("userid") String userid,
            @RequestBody Business request) {
        return businessSignupService.businesssignup(request);
    }

    @PostMapping("/paymentmethod")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PaymentMethodSignupResponse> paymentmethodsignup(@RequestBody PaymentMethod request) {
        return businessSignupService.paymentmethodsignup(request);
    }

    @PostMapping("/userprofile")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserProfileVerifyResponse> userprofilesignup(@RequestBody UserProfileVerifyRequest request) {
        return businessSignupService.userprofilesignup(request);
    }

    @PostMapping("/finish")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FinishSignupResponse> signup(@RequestBody FinishSignupRequest request) {
        return businessSignupService.finish(request);
    }

    @GetMapping("/basicconfig")
    public Flux<Config> findbasicconfig(){
        return businessSignupService.findbasicconfig();
    }


    @GetMapping("/hello")
    public Mono<String> hello(){
        return Mono.just("Hello world");
    }

}
