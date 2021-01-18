package com.spontzoug.server.auth;

import com.spontzoug.server.http.*;
import com.spontzoug.server.model.*;
import com.spontzoug.server.model.User;
import com.spontzoug.server.redis.ISessionService;
import com.spontzoug.server.repository.ICustomerRepository;
import com.spontzoug.server.repository.IUserBusinessLinkRepository;
import com.spontzoug.server.repository.IUserDeviceRepository;
import com.spontzoug.server.repository.IUserRepository;
import com.spontzoug.server.service.IMessageService;
import com.spontzoug.server.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class PasswordService implements IPasswordService {
    @Autowired
    private ITokenService tokenService;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ISessionService sessionService;
    @Autowired
    IVerifyCodeService verifyCodeService;
    @Autowired
    IMessageService messageService;


    public Mono<UserVerifyResponse> userVerifyEmail ( UserVerifyRequest request, HttpHeaders header){
        String codec = verifyCodeService.generate32();
        String email = request.getEmail();
        String ipaddress = header.getHost().getHostString();
        String token = tokenService.generate(SessionConstant.TYPE_TEMP,email);

        Mono<UserVerifyResponse> response = userRepository.findByEmail(email)
                .flatMap( result ->{
                    TemporarySession session = new TemporarySession(
                            new TemporarySessionUtil(result.getUsername(),
                                    result.getEmail(),ipaddress, codec,new Date()), "");
                    return Mono.just( session)
                            //    .flatMap( ses -> signatureService.sign(ses))
                            .flatMap(res -> sessionService.setTemporarySession(token,res))
                            .flatMap(res2 -> messageService.sendEmail(result.getEmail(),"test "+codec))
                            .flatMap( result3 ->{
                                if ( result3.booleanValue() ){
                                    return Mono.just(new UserVerifyResponse(
                                            true,
                                            result.getUsername().equals(request.getUsername()) ? true: false,
                                            result.getEmail().equals(request.getEmail()) ? true: false,
                                            token, ResponseCode.RESPONSE_SUCCEED));
                                } else{
                                    return Mono.just(new UserVerifyResponse(
                                            false,
                                            result.getUsername().equals(request.getUsername()) ? true: false,
                                            result.getEmail().equals(request.getEmail()) ? true: false,
                                            "", ResponseCode.MESSAGE_UNDELIVERABLE));
                                }
                            }).onErrorResume( e -> {
                                return Mono.just(new UserVerifyResponse(false, false, false, "",e.getMessage()));
                            });
                })
                .switchIfEmpty(
                        Mono.just(new UserVerifyResponse(false,false,false, "", ResponseCode.EMAIL_NOTFOUND))
                );
        return response;
    }

    public Mono<PasswordResetResponse> userResetPassword(PasswordResetRequest request, HttpHeaders header){
        String token = request.getToken();
        String code = request.getCode();
        String newpassword = request.getPassword();
        String ipaddress = header.getHost().getHostString();
        String mesg = "";

        return sessionService.getTemporarySession(token)
                .flatMap( ses -> {
                    if (ses.getData().getIpaddress().equals(ipaddress) &&
                            ses.getData().getCode().equals(code.toUpperCase()) ){
                            return userRepository.findByEmail(ses.getData().getEmail())
                                .flatMap( user -> {
                                    String password = passwordEncoder.encode(newpassword);
                                    user.setPassword(password);
                                    return userRepository.save(user)
                                            .flatMap( re ->{
                                                return Mono.just(new PasswordResetResponse(true,ResponseCode.RESPONSE_SUCCEED));
                                            });
                                }).switchIfEmpty(
                                    Mono.just(new PasswordResetResponse(false,ResponseCode.EMAIL_NOTFOUND))
                                );
                    } else {
                        return Mono.just(new PasswordResetResponse(false,ResponseCode.VERIFY_CODE_ERROR));
                    }
                }).defaultIfEmpty(
                        new PasswordResetResponse(false, ResponseCode.SESSION_TEMP_NOTFOUND)
                );

    }

        public Mono<PasswordChangeResponse> userUpdatePassword(PasswordChangeRequest request){
        String id = request.getId();
        String  oldpassword = request.getOldpassword();
        String  newpassword = request.getNewpassword();
        Mono<PasswordChangeResponse> response = userRepository.findById(id)
                .defaultIfEmpty(new User())
                .flatMap( user ->{
                    if( user.getId() == null){
                        return Mono.just(new PasswordChangeResponse(false,ResponseCode.USER_NOTFOUND));
                    } else {
                        boolean passMatch = passwordEncoder.matches(oldpassword, user.getPassword());
                        if( passMatch){
                            String password = passwordEncoder.encode(newpassword);
                            user.setPassword(password);
                            return  userRepository.save(user)
                                    .flatMap( user1 ->{
                                        return Mono.just(new PasswordChangeResponse(true,ResponseCode.RESPONSE_SUCCEED));
                                    });
                        } else {
                            return Mono.just(new PasswordChangeResponse(false,ResponseCode.PASSWORD_NOTMATCH));
                        }
                    }
                });
        return response;

    }


    public Mono<UserVerifyResponse> customerVerifyEmail ( UserVerifyRequest request, HttpHeaders header){
        String codec = verifyCodeService.generate32();
        String email = request.getEmail();
        String ipaddress = header.getHost().getHostString();
        String token = tokenService.generate(SessionConstant.TYPE_TEMP,email);

        Mono<UserVerifyResponse> response = customerRepository.findByEmail(email)
                .flatMap( result ->{
                    TemporarySession session = new TemporarySession(
                            new TemporarySessionUtil(result.getUsername(),
                                    result.getEmail(),ipaddress, codec,new Date()), "");
                    return Mono.just( session)
                            //    .flatMap( ses -> signatureService.sign(ses))
                            .flatMap(res -> sessionService.setTemporarySession(token,res))
                            .flatMap(res2 -> messageService.sendEmail(result.getEmail(),"test "+codec))
                            .flatMap( result3 ->{
                                if ( result3.booleanValue() ){
                                    return Mono.just(new UserVerifyResponse(
                                            true,
                                            result.getUsername().equals(request.getUsername()) ? true: false,
                                            result.getEmail().equals(request.getEmail()) ? true: false,
                                            token, ResponseCode.RESPONSE_SUCCEED));
                                } else{
                                    return Mono.just(new UserVerifyResponse(
                                            false,
                                            result.getUsername().equals(request.getUsername()) ? true: false,
                                            result.getEmail().equals(request.getEmail()) ? true: false,
                                            "", ResponseCode.MESSAGE_UNDELIVERABLE));
                                }
                            }).onErrorResume( e -> {
                                return Mono.just(new UserVerifyResponse(false, false, false, "",e.getMessage()));
                            });
                })
                .switchIfEmpty(
                        Mono.just(new UserVerifyResponse(false,false,false, "", ResponseCode.EMAIL_NOTFOUND))
                );
        return response;
    }

    public Mono<PasswordResetResponse> customerResetPassword(PasswordResetRequest request, HttpHeaders header){
        String token = request.getToken();
        String code = request.getCode();
        String newpassword = request.getPassword();
        String ipaddress = header.getHost().getHostString();
        String mesg = "";

        return sessionService.getTemporarySession(token)
                .flatMap( ses -> {
                    if (ses.getData().getIpaddress().equals(ipaddress) &&
                            ses.getData().getCode().equals(code.toUpperCase()) ){
                        return customerRepository.findByEmail(ses.getData().getEmail())
                                .flatMap( user -> {
                                    String password = passwordEncoder.encode(newpassword);
                                    user.setPassword(password);
                                    return customerRepository.save(user)
                                            .flatMap( re ->{
                                                return Mono.just(new PasswordResetResponse(true,ResponseCode.RESPONSE_SUCCEED));
                                            });
                                }).switchIfEmpty(
                                        Mono.just(new PasswordResetResponse(false,ResponseCode.EMAIL_NOTFOUND))
                                );
                    } else {
                        return Mono.just(new PasswordResetResponse(false,ResponseCode.VERIFY_CODE_ERROR));
                    }
                }).defaultIfEmpty(
                        new PasswordResetResponse(false, ResponseCode.SESSION_TEMP_NOTFOUND)
                );

    }

    public Mono<PasswordChangeResponse> customerUpdatePassword(PasswordChangeRequest request){
        String id = request.getId();
        String  oldpassword = request.getOldpassword();
        String  newpassword = request.getNewpassword();
        Mono<PasswordChangeResponse> response = customerRepository.findById(id)
                .defaultIfEmpty(new Customer())
                .flatMap( user ->{
                    if( user.getId() == null){
                        return Mono.just(new PasswordChangeResponse(false,ResponseCode.USER_NOTFOUND));
                    } else {
                        boolean passMatch = passwordEncoder.matches(oldpassword, user.getPassword());
                        if( passMatch){
                            String password = passwordEncoder.encode(newpassword);
                            user.setPassword(password);
                            return  customerRepository.save(user)
                                    .flatMap( user1 ->{
                                        return Mono.just(new PasswordChangeResponse(true,ResponseCode.RESPONSE_SUCCEED));
                                    });
                        } else {
                            return Mono.just(new PasswordChangeResponse(false,ResponseCode.PASSWORD_NOTMATCH));
                        }
                    }
                });
        return response;

    }


}