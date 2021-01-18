package com.spontzoug.server.redis;

import com.spontzoug.server.auth.ISignatureService;
import com.spontzoug.server.model.CustomerSession;
import com.spontzoug.server.model.JoinSession;
import com.spontzoug.server.model.TemporarySession;
import com.spontzoug.server.model.UserSession;
import com.spontzoug.server.util.SessionConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Service
public class SessionService implements ISessionService {
    @Value("${session.user.expiration}")
    private String userexpiration;
    @Value("${session.customer.expiration}")
    private String customerexpiration;
    @Value("${session.temporary.expiration}")
    private String temporaryexpiration;
    @Value("${session.signup.expiration}")
    private String signupexpiration;


    @Autowired
    private ISignatureService signatureService;

    @Autowired
    private ReactiveRedisTemplate<String, UserSession> userTemplate;
    @Autowired
    private ReactiveRedisTemplate<String, CustomerSession> customerTemplate;
    @Autowired
    private ReactiveRedisTemplate<String, TemporarySession> temporayTemplate;
    @Autowired
    private ReactiveRedisTemplate<String, JoinSession> joinTemplate;

    @Override
    public Mono<UserSession> getUserSession(final String token){
        log.info("get sesson token:"+ token);
        log.info("get session key:"+ SessionConstant.TYPE_USER+token.hashCode());

        return  userTemplate
                .opsForHash()
                .get(SessionConstant.TYPE_USER+"."+token.hashCode(), token)
                .cast(UserSession.class);


       // return Mono.empty();
    }
    @Override
    public Mono<UserSession> getUpdateUserSession(final String token,UserSession session){
        return Mono.just(new UserSession());
    }
    @Override
    public Mono<Boolean> setUserSession( final String token,final UserSession session){
        Duration usertimeout = Duration.ofSeconds(Integer.parseInt(userexpiration));
        Duration signuptimeout = Duration.ofSeconds(Integer.parseInt(signupexpiration));
        String key = SessionConstant.TYPE_USER+"."+token.hashCode();
        return  signatureService.sign(session)
                .flatMap( ses ->{
                    return userTemplate
                            .opsForHash()
                            .put(key, token, ses)
                            .flatMap( res ->{
                                if ( session.getType().equals(SessionConstant.TYPE_SIGNUP)){
                                    return userTemplate.expire(key,signuptimeout);
                                } else {
                                    return Mono.just(true);
                                  //  return userTemplate.expire(key,usertimeout);
                                }
                            });
                });
    }
    @Override
    public Mono<Boolean> delUserSession( final String token,final String userid){
    //    Duration usertimeout = Duration.ofSeconds(5);
        String key = SessionConstant.TYPE_USER+"."+token.hashCode();
        return userTemplate
                .opsForHash()
                .get(key, token )
                .cast( UserSession.class)
                .flatMap( ses ->{
                    if ( ses.getData().getUid().equals(userid) ) {
                        return userTemplate.opsForHash().remove(key,token)
                                .map( num -> num >0 ? true: false);
                    } else {
                        return Mono.just(false);
                    }
                });
    }


    @Override
    public Mono<CustomerSession> getCustomerSession(final String token){
        log.info("get sesson token:"+ token);
        log.info("get session key:"+ SessionConstant.TYPE_CUST+token.hashCode());
        return  customerTemplate
                .opsForHash()
                .get(SessionConstant.TYPE_CUST+"."+token.hashCode(), token)
                .cast(CustomerSession.class);
    }

    @Override
    public Mono<Boolean> setCustomerSession( final String token,final CustomerSession session){
        Duration customertimeout = Duration.ofSeconds(Integer.parseInt(customerexpiration));
        Duration signuptimeout = Duration.ofSeconds(Integer.parseInt(signupexpiration));
        String key = SessionConstant.TYPE_CUST+"."+token.hashCode();
        return  signatureService.sign(session)
                .flatMap( ses ->{
                    return userTemplate
                            .opsForHash()
                            .put(key, token, ses)
                            .flatMap( res ->{
                                if ( session.getType().equals(SessionConstant.TYPE_SIGNUP)){
                                    return customerTemplate.expire(key,signuptimeout);
                                } else {
                                    return Mono.just(true);
                                    //  return userTemplate.expire(key,usertimeout);
                                }
                            });
                });
    }

    @Override
    public Mono<Boolean> delCustomerSession( final String token,final String userid){
        //    Duration usertimeout = Duration.ofSeconds(5);
        String key = SessionConstant.TYPE_CUST+"."+token.hashCode();
        return userTemplate
                .opsForHash()
                .get(key, token )
                .cast( UserSession.class)
                .flatMap( ses ->{
                    if ( ses.getData().getUid().equals(userid) ) {
                        return userTemplate.opsForHash().remove(key,token)
                                .map( num -> num >0 ? true: false);
                    } else {
                        return Mono.just(false);
                    }
                });
    }

    @Override
    public Mono<TemporarySession> getTemporarySession(final String token){

        return  temporayTemplate.opsForHash()
                .get(SessionConstant.TYPE_TEMP+"."+token.hashCode(), token)
                .cast(TemporarySession.class)
                .flatMap( ses -> signatureService.verify(ses));
    }

    @Override
    public Mono<Boolean> setTemporarySession(final String token,final TemporarySession session){
        String key = SessionConstant.TYPE_TEMP+"."+token.hashCode();
        Duration  timeout = Duration.ofSeconds(Integer.parseInt(temporaryexpiration));
        return  signatureService.sign(session)
                .flatMap( ses ->{
                    return temporayTemplate
                            .opsForHash()
                            .put(key, token, ses)
                            .flatMap( res ->{
                                return temporayTemplate.expire(key,timeout);
                            });
                });
    }

    @Override
    public Mono<JoinSession> getJoinSession(final String token){
log.info("get join session token:"+ token +" hash:"+token.hashCode());
        return  joinTemplate.opsForHash()
                .get(SessionConstant.TYPE_JOIN+"."+token.hashCode(), token)
                .cast(JoinSession.class)
                .flatMap( ses -> signatureService.verify(ses))
                 .switchIfEmpty(
                         Mono.empty()
                 );
    }

    @Override
    public Mono<Boolean> setJoinSession(final String token,final JoinSession session){
        String key = SessionConstant.TYPE_JOIN+"."+token.hashCode();
        Duration  timeout = Duration.ofSeconds(Integer.parseInt(signupexpiration));
        return  signatureService.sign(session)
                .flatMap( ses ->{
                    return joinTemplate
                            .opsForHash()
                            .put(key, token, ses)
                            .flatMap( res ->{
                                return joinTemplate.expire(key,timeout);
                            });
                });
    }
}