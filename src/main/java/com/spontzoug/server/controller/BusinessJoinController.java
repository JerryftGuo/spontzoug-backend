package com.spontzoug.server.controller;

import com.spontzoug.server.auth.IBusinessJoinService;
import com.spontzoug.server.http.*;

import com.spontzoug.server.model.Business;
import com.spontzoug.server.model.Config;
import com.spontzoug.server.model.PaymentMethod;
import com.spontzoug.server.auth.IBusinessJoinService;
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
@RequestMapping("/businessjoin")
public class BusinessJoinController {
    @Autowired
    private IBusinessJoinService businessjoinService;
    
    @PostMapping("/invitationverify")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<InvitationVerifyResponse> invitationverify(
            @RequestBody InvitationVerifyRequest request,
            @RequestHeader HttpHeaders headers) {
        return businessjoinService.invitationverify(request, headers);
    }
    
    @PostMapping("/userverify")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<InvitationUserVerifyResponse> userverify(
            @RequestBody InvitationUserVerifyRequest request,
            @RequestHeader HttpHeaders headers) {
        return businessjoinService.userverify(request, headers);
    }
    
    @PostMapping("/codeverify")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CodeVerifyResponse> codeverify(
            @RequestBody CodeVerifyRequest request,
            @RequestHeader HttpHeaders headers) {
        return businessjoinService.codeverify(request, headers);
    }
    @PostMapping("/innercodeverify")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<InnerCodeVerifyResponse> innercodeverify(
            @RequestBody InnerCodeVerifyRequest request
    ) {
        return businessjoinService.innercodeverify(request);
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<SignUpResponse> userjoin(@RequestBody SignUpRequest request, @RequestHeader HttpHeaders header) {
        return businessjoinService.userjoin(request, header);
    }


    @PostMapping("/userprofile")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserProfileVerifyResponse> userprofilesignup(@RequestBody UserProfileVerifyRequest request) {
        return businessjoinService.userprofilesignup(request);
    }

    @PostMapping("/finish")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FinishSignupResponse> signup(@RequestBody FinishSignupRequest request) {
        return businessjoinService.finish(request);
    }

    @GetMapping("/basicconfig")
    public Flux<Config> findbasicconfig(){
        return businessjoinService.findbasicconfig();
    }

}
