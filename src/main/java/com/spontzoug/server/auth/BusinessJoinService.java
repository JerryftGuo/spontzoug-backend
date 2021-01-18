package com.spontzoug.server.auth;

import com.spontzoug.server.http.*;
import com.spontzoug.server.model.*;
import com.spontzoug.server.model.User;
import com.spontzoug.server.redis.IInvitationService;
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
@Component("IBusinessJoinService")
public class BusinessJoinService implements IBusinessJoinService {
    @Autowired
    private ITokenService tokenService;
    @Autowired
    private ITotpManager totpManager;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserBusinessLinkRepository userBusinessLinkRepository;
    @Autowired
    private IUserProfileRepository userProfileRepository;
    @Autowired
    private IBusinessRepository businessRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ISessionService sessionService;
    @Autowired
    private IInvitationService invitationService;
    @Autowired
    private SecureRandom secureRandom;
    @Autowired
    private IMessageService messageService;
    @Autowired
    private IVerifyCodeService verifyCodeService;
    @Autowired
    private InnerCodeVerifyService innerCodeVerifyService;

    @Autowired
    private IConfigRepository configRepository;

    public  Mono<SignUpResponse> userjoin(SignUpRequest request, HttpHeaders header){
        String deviceid = request.getDeviceid();
        String token= request.getToken();
        String password = passwordEncoder.encode(request.getPassword());
        String secret = totpManager.generateSecret();
        String ipaddress = header.getHost().getHostString();
        String sesroles = Roles.RL_ENT_SINGUP;
        Date created = new Date();
        log.info("join.token"+ token +" deviceid:"+deviceid +" address:"+ipaddress);

        // User user = new User(null, username, request.getEmail(), password, "active", secret, true, roles, created, created);
        // User tmpUser;
        //  TemporarySession tmpSes;
        Mono<SignUpResponse> response =
                sessionService.getJoinSession(token)
                        .flatMap(  ses -> {
                            SignUpResponse resp;
                            String username = ses.getData().getUsername();
                            String email = ses.getData().getEmail();
                            String role = ses.getData().getRole();
                            String bizid = ses.getData().getBusinessid();
                            String staffid = ses.getData().getStaffid();
                            return userRepository.findByUsernameOrEmail(username,email)
                                    .defaultIfEmpty(new User(null, username, email, password, "active",  true, false,false,0,"","",created, created, created))
                                    .flatMap(result -> {
                                        if (result.getId() == null) {
                                            return userRepository.save(result)
                                                    //    .doOnNext( user -> tmpUser = user)
                                                    .flatMap(result2 -> {
                                                        String uid = result2.getId();
                                                        String token1 = tokenService.generate(SessionConstant.TYPE_USER, result2.getId());
                                                        return Mono.just(new UserSession( SessionConstant.TYPE_SIGNUP,
                                                                new UserSessionUtil( result2.getId(), result2.getUsername(),
                                                                        sesroles, "",
                                                                        ipaddress, deviceid, new Date(), new Date()),
                                                                "sign"))
                                                                .flatMap(res -> sessionService.setUserSession(token1, res))
                                                                .flatMap(result3 -> {
                                                                    if (result3) {
                                                                        String adminrole = role;

                                                                        return businessRepository.findById(bizid)
                                                                        .flatMap( biz ->{
                                                                            BusinessRole bizrole = new BusinessRole(true,bizid,staffid,biz.getBusinessname(),adminrole);
                                                                            return Mono.just(new UserBusinessLink("",uid,biz.getIndustry(),biz.getSubtype(),biz.getAddress().getProvince(),bizrole))
                                                                                    .flatMap( rest -> userBusinessLinkRepository.save(rest))
                                                                                    .flatMap( res -> {
                                                                                        SignUpResponse signUpResponse = new SignUpResponse(true, false, uid, username, token1, secret);
                                                                                        return Mono.just(signUpResponse);
                                                                                    });
                                                                        });
                                                                    } else {
                                                                        SignUpResponse signUpResponse = new SignUpResponse(false, false, uid, username, "", secret);
                                                                        return Mono.just(signUpResponse);
                                                                    }
                                                                });
                                                    });
                                        } else {
                                            return Mono.just(new SignUpResponse(false, true, "", username, "", ""));
                                        }
                                    }).next();
                            // return  resp;
                        }).defaultIfEmpty(
                        new SignUpResponse(false, false, "", "", "", "")
                ).onErrorResume( e ->{
                            return Mono.just(new SignUpResponse(false, false, "", "", "", ""));
                        }
                );
        return response;
    }

    public Mono<InvitationVerifyResponse> invitationverify ( InvitationVerifyRequest request, HttpHeaders header){
        String invitation = request.getInvitation();
        String ipaddress = header.getHost().getHostString();
        String token = tokenService.generate(SessionConstant.TYPE_JOIN,invitation);
        return invitationService.find(invitation)
                .flatMap( inv ->{
                      return Mono.just(new JoinSession(new JoinSessionUtil(
                                inv.getBusinessid(), inv.getStaffid(), inv.getRole(),"","",ipaddress,"", new Date()
                            ),""))
                            .flatMap( ses ->{
                                 return sessionService.setJoinSession(token, ses)
                                         .then(
                                         Mono.just(new InvitationVerifyResponse(true,token,""))
                                            );
                            });
                }).defaultIfEmpty(
                   new InvitationVerifyResponse(false,"",ResponseCode.VERIFY_CODE_ERROR)
                );
    }

    public Mono<CodeVerifyResponse> codeverify ( CodeVerifyRequest request, HttpHeaders header){
        String token = request.getToken();
        String code = request.getCode();
        String ipaddress = header.getHost().getHostString();
        String mesg = "";
        return sessionService.getJoinSession(token)
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

    public Mono<InnerCodeVerifyResponse> innercodeverify ( InnerCodeVerifyRequest request){
        return innerCodeVerifyService.create(request);
    }


    public Mono<InvitationUserVerifyResponse> userverify ( InvitationUserVerifyRequest request, HttpHeaders header){
        String codec = verifyCodeService.generate32();
        String email = request.getEmail();
        String username = request.getUsername();
        String token = request.getToken();
        String ipaddress = header.getHost().getHostString();

        Mono<InvitationUserVerifyResponse> response = userRepository.findByUsernameOrEmail(username, email)
                .take(1)
                .flatMap( result ->{
                    return Mono.just(new InvitationUserVerifyResponse(
                            false,
                            result.getUsername().equals(request.getUsername()) ? true: false,
                            result.getEmail().equals(request.getEmail()) ? true: false ));
                })
                .switchIfEmpty(
                        sessionService.getJoinSession(token)
                        .flatMap( ses ->{
                            JoinSession s= ses;
                            s.getData().setUsername(username);
                            s.getData().setEmail(email);
                            s.getData().setCode(codec);
                            return  sessionService.setJoinSession(token, s);
                        })
                        .flatMap(res2 -> messageService.sendEmail(email,"test "+codec))
                        .flatMap( result3 ->{
                                    if ( result3.booleanValue() ){
                                        return Mono.just(new InvitationUserVerifyResponse(true,false,false));
                                    } else{
                                        return Mono.just(new InvitationUserVerifyResponse(false,false,false));
                                    }
                        }).defaultIfEmpty(
                                    new InvitationUserVerifyResponse(false, false, false)
                        )
                ).next();
        // return    Mono.just( new UserVerifyResponse(false,false));

        return response;
    }

    public Mono<UserProfileVerifyResponse> userprofilesignup (UserProfileVerifyRequest request){
        return innerCodeVerifyService.verify(request.getToken(), request.getCode())
                .flatMap( res ->{
                    if( res.booleanValue()){
                        return userProfileRepository.save(request.getProfile()).flatMap(
                                result ->{
                                    String message = "";
                                    UserProfileVerifyResponse signupResponse = new UserProfileVerifyResponse(true, message);
                                    return Mono.just(signupResponse);
                                }
                        );
                    } else {
                        return Mono.just(new UserProfileVerifyResponse(false, ResponseCode.VERIFY_CODE_ERROR));
                    }
                });
    }

    public Flux<Config> findbasicconfig (){
        return configRepository.findBasicConfig();
    }

    public Mono<FinishSignupResponse> finish (FinishSignupRequest request){
        return Mono.just(new FinishSignupResponse());
    }
}