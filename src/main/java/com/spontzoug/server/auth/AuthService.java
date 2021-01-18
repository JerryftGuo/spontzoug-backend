package com.spontzoug.server.auth;

import com.spontzoug.server.http.*;
import com.spontzoug.server.model.*;
import com.spontzoug.server.model.User;
import com.spontzoug.server.redis.ISessionService;
import com.spontzoug.server.repository.ICustomerDeviceRepository;
import com.spontzoug.server.repository.IUserBusinessLinkRepository;
import com.spontzoug.server.repository.IUserDeviceRepository;
import com.spontzoug.server.repository.IUserRepository;
import com.spontzoug.server.service.ICustomerService;
import com.spontzoug.server.service.IMessageService;
import com.spontzoug.server.service.IUserService;
import com.spontzoug.server.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Component("IAuthService")
public class AuthService implements com.spontzoug.server.auth.IAuthService {
    @Value("${account.try.limit}")
    private String trylimit;
    @Value("${account.unlock.interval}")
    private String unlockinterval;

    @Autowired
    private ITokenService tokenService;
    @Autowired
    private ITotpManager totpManager;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ISessionService sessionService;
    @Autowired
    IUserDeviceRepository userDeviceRepository;
    @Autowired
    ICustomerDeviceRepository customerDeviceRepository;
    @Autowired
    IUserBusinessLinkRepository userBusinessLinkRepository;
    @Autowired
    IVerifyCodeService verifyCodeService;
    @Autowired
    IMessageService messageService;

    public Mono<UserSignInResponse> usersignin(UserSignInRequest request, HttpHeaders headers){
        int interval = Integer.parseInt(unlockinterval);
        int limit = Integer.parseInt(trylimit);

        String codec = verifyCodeService.generate32();
        String username = request.getUsername().trim().toLowerCase();
        String host = headers.getHost().getHostString();
        String ip = request.getIp();
        String mac = request.getMac();
        String device = request.getDevice();
        List<String> roles = new ArrayList<>();
        roles.add(Roles.RL_ENT_SIGNIN);
        BusinessRole  role = new BusinessRole();
        String signintoken = tokenService.generate(SessionConstant.TYPE_TEMP,username);
        TemporarySession signinSession = new TemporarySession(
                new TemporarySessionUtil(
                    username, "", host, codec, new Date()
                ), "sign");

        Mono<UserSignInResponse> response = userService.findByUsername(username)
                .flatMap( user2 ->{   // check device list
                    if ( user2.getLocked() ){
                        if ( (Calendar.getInstance().getTimeInMillis() - user2.getLocktime().getTime()) > (interval *1000) ){
                            user2.setLocked(false);
                            user2.setTried(0);
                            user2.setLocktime(Calendar.getInstance().getTime());
                            return userService.update(user2)
                                    .flatMap( user3 ->{
                                        return Mono.just(new UserSignInResponse(false, false, false, 0, 0, "", "", "","", role, "", ResponseCode.SESSION_ACCOUNT_LOCKED));
                                    });
                        } else {
                            return Mono.just(new UserSignInResponse(false, false, false, 0, 0, "", "", "","", role, "", ResponseCode.SESSION_ACCOUNT_LOCKED));
                        }
                    } else if( !passwordEncoder.matches(request.getPassword(), user2.getPassword())) {
                        if( user2.getTried() >= limit ){
                            user2.setLocked(true);
                            user2.setLocktime(Calendar.getInstance().getTime());
                            return userService.update(user2)
                                    .flatMap( user3 -> {
                                        return Mono.just(new UserSignInResponse(false, false, false, 0, 0, "", "","", "", role, "", ResponseCode.SESSION_ACCOUNT_LOCKED));
                                    });
                        } else {
                                user2.setTried(user2.getTried()+1);
                                return userService.update(user2)
                                .flatMap( user4 ->{
                                    return Mono.just(new UserSignInResponse(false,false,false,user4.getTried(),limit,"","","","",role,"",ResponseCode.SESSION_PASSWORD_INVALID));
                                });
                        }
                    } else if( user2.getSignedin()) {
                        return Mono.just(new UserSignInResponse(false,false,false,0,0,"","","","",role,"",ResponseCode.SESSION_DAUL_SIGNIN));
                    } else if( !user2.getEnabled()){
                        return Mono.just(new UserSignInResponse(false,false,false,0,0,"","","","",role,"",ResponseCode.SESSION_USER_DISABLED));
                    } else {
                        if( user2.getTried() > 0 ){
                               user2.setTried(0);
                               userService.update(user2);
                        }
                        return userDeviceRepository.findByUserid(user2.getId())
                                .filter(dev -> {
                                    return dev.getDevice().getHost().equals(host) &&
                                            dev.getDevice().getIp().equals(ip) &&
                                            dev.getDevice().getMac().equals(mac) &&
                                            dev.getDevice().getName().equals(device);
                                }).flatMap(dev1 -> { // the used device found
                                    String token = tokenService.generate(SessionConstant.TYPE_USER, user2.getId());
                                    String session = SessionConstant.TYPE_USER+"."+token.hashCode();
                                    return userBusinessLinkRepository.findByUserid(user2.getId())
                                            .filter(lnk -> lnk.getRole().getActive())
                                            .flatMap(link -> {
                                                return Mono.just(link.getRole())
                                                        .flatMap(rl -> {
                                                            return Mono.just(new UserSession(SessionConstant.TYPE_USER,
                                                                    new UserSessionUtil(
                                                                            user2.getId(), user2.getUsername(),
                                                                            rl.getRole(), rl.getBusinessid(),
                                                                            host, device, new Date(), new Date()
                                                                    ), "sign"))
                                                                    .flatMap(res -> sessionService.setUserSession(token, res))
                                                                    .flatMap(result -> {
                                                                        if (result.booleanValue()) { // success create session signin
                                                                            User user = user2;
                                                                            user.setSession(session);
                                                                            user.setToken(token);
                                                                            user.setSignedin(true);
                                                                            user.setModified(new Date());
                                                                            return userService.update(user)
                                                                                    .then(
                                                                                            Mono.just(new UserSignInResponse(true, false, false, 0,0,user2.getId(), link.getIndustry(), link.getSubtype(),link.getRegion(), rl, token, ""))
                                                                                    );
                                                                        } else { // failed create session
                                                                            return Mono.just(new UserSignInResponse(false, false, false, 0,0,user2.getId(), "", "","", role, "", ResponseCode.SESSION_SET_ERROR));
                                                                        }
                                                                    });
                                                        });
                                            });
                                    //    return    Mono.just(new UserSignInResponse(false, false, true,user2.getId(), roles,""));
                                }).switchIfEmpty( // new device , verify required
                                        Mono.just(signinSession)
                                                .flatMap(res -> sessionService.setTemporarySession(
                                                        signintoken, res))
                                                .flatMap(res2 -> messageService.sendEmail(user2.getEmail(), "test " + codec))
                                                .flatMap(result3 -> {
                                                    if (result3.booleanValue()) {
                                                        return Mono.just(new UserSignInResponse(false, false, true, 0,0,"", "", "","", role, signintoken, ""));
                                                    } else {
                                                        return Mono.just(new UserSignInResponse(false, false, false, 0,0,"", "", "", "",role, "", ResponseCode.SESSION_SENDMESSAGE_ERROR));
                                                    }
                                                }).onErrorResume(e -> {
                                            return Mono.just(new UserSignInResponse(false, false, false, 0,0, user2.getId(), "", "", "",role, "", ResponseCode.SESSION_SYSTEM_ERROR));
                                        })
                                ).next();
                    }
                }).switchIfEmpty( // not found, password error, signed in already
                        Mono.just(new UserSignInResponse(false,false,false,0,0,"","","","",role,"",ResponseCode.SESSION_USER_NOTFOUND))
                );
        return response;
    }


    public Mono<SignOutResponse> usersignout(SignOutRequest request){
            String userid = request.getUid();
            String token = request.getToken();

            Mono<SignOutResponse> response = userService.findById(userid)
            .flatMap( user ->{   // found
                        User u = user;
                        u.setSession("");
                        u.setToken("");
                        u.setSignedin(false);
                        return userService.update(u)
                                .then(
                                       sessionService.delUserSession(token,userid)
                                       .flatMap( res ->{
                                            if( res.booleanValue()){
                                                return  Mono.just(new SignOutResponse(true,""));
                                            } else {
                                                return Mono.just(new SignOutResponse(false,ResponseCode.SESSION_USER_INVALID));
                                            }
                                       })
                                );
            }).switchIfEmpty( // not found
                   Mono.just(new SignOutResponse(false,ResponseCode.SESSION_USER_NOTFOUND))
            );
            return response;
    }

    public Mono<UserSignInResponse> usercodeverify ( SignInVerifyRequest request, HttpHeaders header) {
        String token = request.getToken();
        String code = request.getCode();
        String ip = request.getIp();
        String mac = request.getMac();
        String device = request.getDevice();
        String host = header.getHost().getHostString();
        String mesg = "";
        BusinessRole role = new BusinessRole();
        return sessionService.getTemporarySession(token)
                .filter(ses -> {
                    return (ses.getData().getIpaddress().equals(host) &&
                            ses.getData().getCode().equals(code.toUpperCase()));
                })
                .flatMap(ses1 -> {  //code ok do the sign in
                    String username = ses1.getData().getUsername();
                    return userService.findByUsername(username)
                        .flatMap(result -> {
                            String uid = result.getId();
                            String token1 = tokenService.generate(SessionConstant.TYPE_USER, result.getId());
                            String session1 = SessionConstant.TYPE_USER+"."+token1.hashCode();
                            return userBusinessLinkRepository.findByUserid(uid)
                                    .filter( link -> link.getRole().getActive() )
                                    .flatMap( lnk -> { // create session
                                        UserDevice dev = new UserDevice("",lnk.getUserid(),
                                                new DeviceInfo(host, ip, mac, device,true, new Date()) );
                                        return userDeviceRepository.save( dev)
                                                .then(
                                                    Mono.just(new UserSession(SessionConstant.TYPE_USER,
                                                        new UserSessionUtil(result.getId(), result.getUsername(),
                                                            lnk.getRole().getRole(), lnk.getRole().getBusinessid(),
                                                            host, device, new Date(), new Date() ),
                                                    "sign"))
                                                    .flatMap(ses2 -> sessionService.setUserSession(token1, ses2))
                                                    .flatMap(result3 -> {
                                                        if (result3.booleanValue()) { // verify success
                                                            User user = result;
                                                            user.setSession(session1);
                                                            user.setToken(token1);
                                                            user.setSignedin(true);
                                                            user.setModified( new Date());
                                                            return userService.update(user)
                                                                    .then(
                                                                    Mono.just(new UserSignInResponse(true, false,false, 0,0,uid, lnk.getIndustry(),lnk.getSubtype(),lnk.getRegion(),lnk.getRole(),token1,"" ))
                                                            );
                                                        } else {
                                                            return Mono.just(new UserSignInResponse(false, false,false, 0,0,"", "","","",role, "",ResponseCode.SESSION_SET_ERROR));
                                                        }
                                                    })
                                                );
                                    })
                                    .defaultIfEmpty(
                                            new UserSignInResponse(false, false,false, 0,0,"", "","","",role, "",ResponseCode.SESSION_USER_BUSINESS_LINK_NOTFOUND)
                                    ).next();
                        });
                }).defaultIfEmpty(
                    new UserSignInResponse(false, false,false, 0,0,"", "","","",role, "",ResponseCode.SESSION_TEMP_INVALID)
                );
    }


    public Mono<CustomerSignInResponse> customersignin(CustomerSignInRequest request, HttpHeaders headers){
        int interval = Integer.parseInt(unlockinterval);
        int limit = Integer.parseInt(trylimit);

        String codec = verifyCodeService.generate32();
        String username = request.getUsername().trim().toLowerCase();
        String host = headers.getHost().getHostString();
        String ip = request.getIp();
        String mac = request.getMac();
        String device = request.getDevice();

        String  role = Roles.RL_CUSTOMER;
        String signintoken = tokenService.generate(SessionConstant.TYPE_TEMP,username);
        TemporarySession signinSession = new TemporarySession(
                new TemporarySessionUtil(
                        username, "", host, codec, new Date()
                ), "sign");

        Mono<CustomerSignInResponse> response = customerService.findByCustomername(username)
                .flatMap( user2 ->{   // check device list
                    if ( user2.getLocked() ){
                        if ( (Calendar.getInstance().getTimeInMillis() - user2.getLocktime().getTime()) > (interval *1000) ){
                            user2.setLocked(false);
                            user2.setTried(0);
                            user2.setLocktime(Calendar.getInstance().getTime());
                            return customerService.update(user2)
                                    .flatMap( user3 ->{
                                        return Mono.just(new CustomerSignInResponse(false, false, false, 0, 0, "", "", user2.getRole(), "", ResponseCode.SESSION_ACCOUNT_LOCKED));
                                    });
                        } else {
                            return Mono.just(new CustomerSignInResponse(false, false, false, 0, 0, "",  "", user2.getRole(), "", ResponseCode.SESSION_ACCOUNT_LOCKED));
                        }
                    } else if( !passwordEncoder.matches(request.getPassword(), user2.getPassword())) {
                        if( user2.getTried() >= limit ){
                            user2.setLocked(true);
                            user2.setLocktime(Calendar.getInstance().getTime());
                            return customerService.update(user2)
                                    .flatMap( user3 -> {
                                        return Mono.just(new CustomerSignInResponse(false, false, false, 0, 0, "", "",user2.getRole(), "", ResponseCode.SESSION_ACCOUNT_LOCKED));
                                    });
                        } else {
                            user2.setTried(user2.getTried()+1);
                            return customerService.update(user2)
                                    .flatMap( user4 ->{
                                        return Mono.just(new CustomerSignInResponse(false,false,false,user4.getTried(),limit,"","",user2.getRole(),"",ResponseCode.SESSION_PASSWORD_INVALID));
                                    });
                        }
                    } else if( user2.getSignedin()) {
                        return Mono.just(new CustomerSignInResponse(false,false,false,0,0,"","",user2.getRole(),"",ResponseCode.SESSION_DAUL_SIGNIN));
                    } else if( !user2.getEnabled()){
                        return Mono.just(new CustomerSignInResponse(false,false,false,0,0,"","",user2.getRole(),"",ResponseCode.SESSION_USER_DISABLED));
                    } else {
                        if( user2.getTried() > 0 ){
                            user2.setTried(0);
                            customerService.update(user2);
                        }
                        return customerDeviceRepository.findByCustomerid(user2.getId())
                                .filter(dev -> {
                                    return dev.getDevice().getHost().equals(host) &&
                                            dev.getDevice().getIp().equals(ip) &&
                                            dev.getDevice().getMac().equals(mac) &&
                                            dev.getDevice().getName().equals(device);
                                }).flatMap(dev1 -> { // the used device found
                                    String token = tokenService.generate(SessionConstant.TYPE_CUST, user2.getId());
                                    String session = SessionConstant.TYPE_CUST+"."+token.hashCode();
                                    return Mono.just(new CustomerSession(SessionConstant.TYPE_CUST,
                                            new CustomerSessionUtil(
                                            user2.getId(), user2.getUsername(),
                                            user2.getRole(), host, device, new Date(), new Date()), "sign")
                                    )
                                .flatMap(res -> sessionService.setCustomerSession(token, res))
                                .flatMap(result -> {
                                        if (result.booleanValue()) { // success create session signin
                                            Customer user = user2;
                                            user.setSession(session);
                                            user.setToken(token);
                                            user.setSignedin(true);
                                            user.setModified(new Date());
                                            return customerService.update(user)
                                             .then(
                                                   Mono.just(new CustomerSignInResponse(true, false, false, 0,0,user2.getId(), "", user2.getRole(), token, ""))
                                             );
                                        } else { // failed create session
                                              return Mono.just(new CustomerSignInResponse(false, false, false, 0,0,user2.getId(), "", "", "", ResponseCode.SESSION_SET_ERROR));
                                        }
                                    });
                                }).switchIfEmpty( // new device , verify required
                                        Mono.just(signinSession)
                                                .flatMap(res -> sessionService.setTemporarySession(
                                                        signintoken, res))
                                                .flatMap(res2 -> messageService.sendEmail(user2.getEmail(), "test " + codec))
                                                .flatMap(result3 -> {
                                                    if (result3.booleanValue()) {
                                                        return Mono.just(new CustomerSignInResponse(false, false, true, 0,0,"", "", "", signintoken, ""));
                                                    } else {
                                                        return Mono.just(new CustomerSignInResponse(false, false, false, 0,0,"", "","", "", ResponseCode.SESSION_SENDMESSAGE_ERROR));
                                                    }
                                                }).onErrorResume(e -> {
                                            return Mono.just(new CustomerSignInResponse(false, false, false, 0,0, user2.getId(), "","", "", ResponseCode.SESSION_SYSTEM_ERROR));
                                        })
                                ).next();
                    }
                }).switchIfEmpty( // not found, password error, signed in already
                        Mono.just(new CustomerSignInResponse(false,false,false,0,0,"","","","",ResponseCode.SESSION_USER_NOTFOUND))
                );
        return response;
    }


    public Mono<SignOutResponse> customersignout(SignOutRequest request){
        String userid = request.getUid();
        String token = request.getToken();

        Mono<SignOutResponse> response = customerService.findById(userid)
                .flatMap( user ->{   // found
                    Customer u = user;
                    u.setSession("");
                    u.setToken("");
                    u.setSignedin(false);
                    return customerService.update(u)
                            .then(
                                    sessionService.delCustomerSession(token,userid)
                                            .flatMap( res ->{
                                                if( res.booleanValue()){
                                                    return  Mono.just(new SignOutResponse(true,""));
                                                } else {
                                                    return Mono.just(new SignOutResponse(false,ResponseCode.SESSION_USER_INVALID));
                                                }
                                            })
                            );
                }).switchIfEmpty( // not found
                        Mono.just(new SignOutResponse(false,ResponseCode.SESSION_USER_NOTFOUND))
                );
        return response;
    }

    public Mono<CustomerSignInResponse> customercodeverify ( SignInVerifyRequest request, HttpHeaders header) {
        String token = request.getToken();
        String code = request.getCode();
        String ip = request.getIp();
        String mac = request.getMac();
        String device = request.getDevice();
        String host = header.getHost().getHostString();
        String role = Roles.RL_CUSTOMER;
        return sessionService.getTemporarySession(token)
                .filter(ses -> {
                    return (ses.getData().getIpaddress().equals(host) &&
                            ses.getData().getCode().equals(code.toUpperCase()));
                })
                .flatMap(ses1 -> {  //code ok do the sign in
                    String username = ses1.getData().getUsername();
                    return customerService.findByCustomername(username)
                            .flatMap(result -> {
                                String uid = result.getId();
                                String token1 = tokenService.generate(SessionConstant.TYPE_CUST, result.getId());
                                String session1 = SessionConstant.TYPE_CUST+"."+token1.hashCode();

                                CustomerDevice dev = new CustomerDevice("",result.getId(),
                                    new DeviceInfo(host, ip, mac, device,true, new Date()) );
                                return customerDeviceRepository.save( dev)
                                     .then(
                                          Mono.just(new CustomerSession(SessionConstant.TYPE_CUST,
                                                 new CustomerSessionUtil(result.getId(), result.getUsername(),
                                                     role, host, device, new Date(), new Date() ),
                                                   "sign")
                                          )
                                          .flatMap(ses2 -> sessionService.setCustomerSession(token1, ses2))
                                          .flatMap(result3 -> {
                                                        if (result3.booleanValue()) { // verify success
                                                            Customer user = result;
                                                            user.setSession(session1);
                                                            user.setToken(token1);
                                                            user.setSignedin(true);
                                                            user.setModified( new Date());
                                                            return customerService.update(user)
                                                            .then(
                                                                 Mono.just(new CustomerSignInResponse(true, false,false, 0,0,uid, "",role,token1,"" ))
                                                            );
                                                        } else {
                                                             return Mono.just(new CustomerSignInResponse(false, false,false, 0,0,"", "",role, "",ResponseCode.SESSION_SET_ERROR));
                                                        }
                                          })
                                );
                            });
                }).defaultIfEmpty(
                        new CustomerSignInResponse(false, false,false, 0,0,"", "",role, "",ResponseCode.SESSION_TEMP_INVALID)
                );
    }

    public Mono<UserSignInResponse> updatePassword(UserSignInRequest request){
        return Mono.empty();
    }


}