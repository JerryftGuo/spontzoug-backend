package com.spontzoug.server.auth;

import com.spontzoug.server.http.*;
import com.spontzoug.server.model.*;
import com.spontzoug.server.model.User;
import com.spontzoug.server.redis.ISessionService;
import com.spontzoug.server.redis.InnerCodeVerifyService;
import com.spontzoug.server.repository.*;
import com.spontzoug.server.service.IBusinessService;
import com.spontzoug.server.service.IMessageService;
import com.spontzoug.server.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Slf4j
@Component("ICustomerSignupService")
public class CustomerSignupService implements ICustomerSignupService {
    @Autowired
    private ITokenService tokenService;
    @Autowired
    private ITotpManager totpManager;
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ISessionService sessionService;


    @Autowired
    private SecureRandom secureRandom;
    @Autowired
    private IMessageService messageService;
    @Autowired
    private IVerifyCodeService verifyCodeService;
    @Autowired
    private InnerCodeVerifyService innerCodeVerifyService;

    @Autowired
    private ICustomerProfileRepository customerProfileRepository;
    @Autowired
    private IConfigRepository configRepository;

    public  Mono<SignUpResponse> usersignup(SignUpRequest request, HttpHeaders header){
        String deviceid = request.getDeviceid();
        String token= request.getToken();
        String password = passwordEncoder.encode(request.getPassword());
        String secret = totpManager.generateSecret();
        String ipaddress = header.getHost().getHostString();

        String sesroles = Roles.RL_CUST_SIGNUP;
        Date created = new Date();
        log.info("signup.token"+ token +" deviceid:"+deviceid +" address:"+ipaddress);

        Mono<SignUpResponse> response =
                sessionService.getTemporarySession(token)
                        // .doOnNext( ses1 -> { tmpSes.setUsername(ses1.getUsername());} )
                        .flatMap(  ses -> {
                            SignUpResponse resp;
                            String username = ses.getData().getUsername();
                            String email = ses.getData().getEmail();
                            return customerRepository.findByCustomernameOrEmail(username,email)
                                    .defaultIfEmpty(new Customer(null, username, email, password, "active",  Roles.RL_CUSTOMER,true, false,false,0, "","",created,created, created))
                                    .flatMap(result -> {
                                        if (result.getId() == null) {
                                            return customerRepository.save(result)
                                                    //    .doOnNext( user -> tmpUser = user)
                                                    .flatMap(result2 -> {
                                                        SignUpResponse signUpResponse = new SignUpResponse(true, false, "", "", "", ResponseCode.RESPONSE_SUCCEED);
                                                        return Mono.just(signUpResponse);
                                                    });
                                        } else {
                                            return Mono.just(new SignUpResponse(false, true, "", username, "", ResponseCode.USER_EXIST));
                                        }
                                    }).next();
                            // return  resp;
                        }).defaultIfEmpty(
                            new SignUpResponse(false, false, "", "", "", ResponseCode.SESSION_TEMP_NOTFOUND)
                ).onErrorResume( e ->{
                            return Mono.just(new SignUpResponse(false, false, "", "", "", e.getMessage()));
                        }
                );
        return response;
    }


    public Mono<CodeVerifyResponse> codeverify ( CodeVerifyRequest request, HttpHeaders header){
        String token = request.getToken();
        String code = request.getCode();
        String ipaddress = header.getHost().getHostString();
        return sessionService.getTemporarySession(token)
                .flatMap( ses -> {
                    if ( !ses.getData().getIpaddress().equals(ipaddress) ){
                        return Mono.just(new CodeVerifyResponse(false,ResponseCode.SESSION_TEMP_INVALID));
                    } else if ( !ses.getData().getCode().equals(code.toUpperCase()) ){
                        return Mono.just(new CodeVerifyResponse(false,ResponseCode.VERIFY_CODE_ERROR));
                    } else {
                        return Mono.just(new CodeVerifyResponse(true,""));
                    }
                }).defaultIfEmpty(
                        new CodeVerifyResponse(false, ResponseCode.SESSION_TEMP_NOTFOUND)
                );
    }


    public Mono<UserVerifyResponse> userverify ( UserVerifyRequest request, HttpHeaders header){
        String codec = verifyCodeService.generate32();
        String email = request.getEmail();
        String username = request.getUsername();
        String ipaddress = header.getHost().getHostString();
        String token = tokenService.generate(SessionConstant.TYPE_TEMP,email);
        TemporarySession session = new TemporarySession(
                new TemporarySessionUtil(username,email,ipaddress, codec,new Date()), "");
        Mono<UserVerifyResponse> response = customerRepository.findByCustomernameOrEmail(username, email)
                .take(1)
                .flatMap( result ->{
                    return Mono.just(new UserVerifyResponse(
                            false,
                            result.getUsername().equals(request.getUsername()) ? true: false,
                            result.getEmail().equals(request.getEmail()) ? true: false,
                            "",ResponseCode.USER_EMAIL_EXIST));
                })
                .switchIfEmpty(
                        Mono.just( session)
                                //    .flatMap( ses -> signatureService.sign(ses))
                                .flatMap(res -> sessionService.setTemporarySession(token,res))
                                .flatMap(res2 -> messageService.sendEmail(email,"test "+codec))
                                .flatMap( result3 ->{
                                    if ( result3.booleanValue() ){
                                        return Mono.just(new UserVerifyResponse(true,false,false, token,ResponseCode.RESPONSE_SUCCEED));
                                    } else{
                                        return Mono.just(new UserVerifyResponse(false,false,false,"", ResponseCode.MESSAGE_UNDELIVERABLE));
                                    }
                                }).onErrorResume( e -> {
                                    return Mono.just(new UserVerifyResponse(false, false, false, "", e.getMessage()));
                                }
                        )
                ).next();
        return response;
    }

    public Mono<FinishSignupResponse> finish (FinishSignupRequest request){
        return Mono.just(new FinishSignupResponse());
    }
}