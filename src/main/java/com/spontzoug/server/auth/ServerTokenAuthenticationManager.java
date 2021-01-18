package com.spontzoug.server.auth;

import com.spontzoug.server.model.UserSession;
import com.spontzoug.server.redis.ISessionService;
import com.spontzoug.server.util.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Slf4j
public class ServerTokenAuthenticationManager implements ReactiveAuthenticationManager{
    @Autowired
    private ISessionService sessionService;

    public ServerTokenAuthenticationManager( ISessionService sessionService){
        this.sessionService = sessionService;
    }

    @Override
    public Mono<Authentication> authenticate( Authentication authentication)
    {

        String token = (String)authentication.getPrincipal();
        log.info("filter get token:"+ token);
        if ( token.length() >7){
       //     log.info("filter get:"+ token.substring(7));
            String tmp = token.substring(7);
        //    log.info("filter get hasd:"+ token.substring(7).hashCode());
            if ( tmp.startsWith("USER")) {
                return sessionService.getUserSession(tmp)
                        .flatMap(ses -> {
                            ArrayList<GrantedAuthority> roles = new ArrayList<>();
                            String role = ses.getData().getRole();
                            roles.add(new SimpleGrantedAuthority(role));
                            UserPrincipal principal = new UserPrincipal(
                                    ses.getData().getUid(),
                                    ses.getData().getBusinessid()
                            );
                            return Mono.just(
                                    new UsernamePasswordAuthenticationToken(
                                            principal,
                                            ses.getData().getUsername(),
                                            roles));
                        });
            } else  {
                return sessionService.getCustomerSession(tmp)
                        .flatMap(ses -> {
                            ArrayList<GrantedAuthority> roles = new ArrayList<>();
                            String role = ses.getData().getRole();
                            roles.add(new SimpleGrantedAuthority(role));
                            UserPrincipal principal = new UserPrincipal(
                                    ses.getData().getUid(),
                                    ses.getData().getUsername()
                            );
                            return Mono.just(
                                    new UsernamePasswordAuthenticationToken(
                                            principal,
                                            ses.getData().getUsername(),
                                            roles));
                        });
            }
        } else {
            return Mono.empty();
        }
    }
}