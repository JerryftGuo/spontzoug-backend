package com.spontzoug.server.auth;

import com.spontzoug.server.http.*;
import com.spontzoug.server.model.*;
import com.spontzoug.server.model.User;
import com.spontzoug.server.redis.ISessionService;
import com.spontzoug.server.redis.InnerCodeVerifyService;
import com.spontzoug.server.repository.*;
import com.spontzoug.server.service.IBusinessService;
import com.spontzoug.server.service.IMessageService;
import com.spontzoug.server.service.IRegionCityService;
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
import java.util.*;


@Slf4j
@Component("IBusinessSignupService")
public class BusinessSignupService implements IBusinessSignupService {
    @Autowired
    private ITokenService tokenService;
    @Autowired
    private ITotpManager totpManager;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ISessionService sessionService;
    @Autowired
    private IBusinessRepository businessRepository;
    @Autowired
    private IPaymentMethodRepository paymentMethodRepository;
    @Autowired
    private SecureRandom secureRandom;
    @Autowired
    private IMessageService messageService;
    @Autowired
    private IVerifyCodeService verifyCodeService;
    @Autowired
    private InnerCodeVerifyService innerCodeVerifyService;
    @Autowired
    private IUserBusinessLinkRepository userBusinessLinkRepository;
    @Autowired
    private IUserProfileRepository userProfileRepository;
    @Autowired
    private IConfigRepository configRepository;
    @Autowired
    private IRegionCityRepository regionCityRepository;

    public  Mono<SignUpResponse> usersignup(SignUpRequest request, HttpHeaders header){
        String deviceid = request.getDeviceid();
        String token= request.getToken();
        String password = passwordEncoder.encode(request.getPassword());
        String secret = totpManager.generateSecret();
        String ipaddress = header.getHost().getHostString();
        List<String> adminroles = new ArrayList<>();
        adminroles.add(Roles.RL_ENT_ADMIN);
        String sesroles = Roles.RL_ENT_SINGUP;
        Date created = new Date();
        log.info("signup.token"+ token +" deviceid:"+deviceid +" address:"+ipaddress);

        Mono<SignUpResponse> response =
                sessionService.getTemporarySession(token)
               // .doOnNext( ses1 -> { tmpSes.setUsername(ses1.getUsername());} )
                .flatMap(  ses -> {
                    SignUpResponse resp;
                    String username = ses.getData().getUsername();
                    String email = ses.getData().getEmail();
                    return userRepository.findByUsernameOrEmail(username,email)
                            .defaultIfEmpty(new User(null, username, email, password, "active",  true, false,false,0, "","",created,created, created))
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
                                                        SignUpResponse signUpResponse = new SignUpResponse(true, false, uid, username, token1, ResponseCode.RESPONSE_SUCCEED);
                                                        return Mono.just(signUpResponse);
                                                    } else {
                                                        SignUpResponse signUpResponse = new SignUpResponse(false, false, uid, username, "", ResponseCode.SESSION_SYSTEM_ERROR);
                                                        return Mono.just(signUpResponse);
                                                    }
                                            });
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
        //    Mono<SignUpResponse> response = Mono.just( new SignUpResponse(false,"id","user","token","secret"));
        return response;
    }


    public Mono<CodeVerifyResponse> codeverify ( CodeVerifyRequest request, HttpHeaders header){
        String token = request.getToken();
        String code = request.getCode();
        String ipaddress = header.getHost().getHostString();
        String mesg = "";
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

    public Mono<InnerCodeVerifyResponse> innercodeverify ( InnerCodeVerifyRequest request){
        return innerCodeVerifyService.create(request);
    }


    public Mono<UserVerifyResponse> userverify ( UserVerifyRequest request, HttpHeaders header){
        String codec = verifyCodeService.generate32();
        String email = request.getEmail();
        String username = request.getUsername();
        String ipaddress = header.getHost().getHostString();
        String token = tokenService.generate(SessionConstant.TYPE_TEMP,email);
        TemporarySession session = new TemporarySession(
                new TemporarySessionUtil(username,email,ipaddress, codec,new Date()), "");
        Mono<UserVerifyResponse> response = userRepository.findByUsernameOrEmail(username, email)
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
       // return    Mono.just( new UserVerifyResponse(false,false));

        return response;
    }

    public Mono<BusinessSignupResponse> businesssignup (Business business){
        String reg = business.getAddress().getProvince();
        String city = business.getAddress().getCity();
        RegionCity regionCity = new RegionCity("",reg.toUpperCase(), city.toUpperCase(), new Date(), new Date());

        return businessRepository.save(business)
                .then(businessRepository.findByCreator(business.getCreator()).next())
                .flatMap(
                    result ->{
                        String bizid = result.getId();
                        String userid = result.getCreator();
                        String name = result.getBusinessname();
                        String industry = result.getIndustry();
                        String subtype = result.getSubtype();
                        String region = result.getAddress().getProvince();
                        String adminroles = Roles.RL_ENT_ADMIN;
                        BusinessRole role = new BusinessRole(true,bizid,"",name,adminroles);
                        return Mono.just(new UserBusinessLink("",userid,industry,subtype,region,role))
                                .flatMap( rest -> userBusinessLinkRepository.save(rest))
                                .flatMap( res ->{
                                        return regionCityRepository.countByRegionCity(reg.toUpperCase(), city.toUpperCase())
                                                .flatMap( cnt ->{
                                                    if( cnt == 0 ) {
                                                        return regionCityRepository.save(regionCity)
                                                                .flatMap( rg ->{
                                                                    BusinessSignupResponse signupResponse = new BusinessSignupResponse(true, bizid);
                                                                    return Mono.just(signupResponse);
                                                                });
                                                    } else {
                                                        BusinessSignupResponse signupResponse = new BusinessSignupResponse(true, bizid);
                                                        return Mono.just(signupResponse);
                                                    }
                                        });
                                });
                    }
                )
                .retry(1)
                .onErrorResume( (e) -> {
                       BusinessSignupResponse signupResponse = new BusinessSignupResponse(false, "");
                       return Mono.just(signupResponse);
                     }
                );
    }
    public Mono<PaymentMethodSignupResponse> paymentmethodsignup (PaymentMethod paymentMethod){
        return paymentMethodRepository.save(paymentMethod).flatMap(
                result ->{
                    String message = "";
                    PaymentMethodSignupResponse signupResponse = new PaymentMethodSignupResponse(true, message);
                    return Mono.just(signupResponse);
                }
        );
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