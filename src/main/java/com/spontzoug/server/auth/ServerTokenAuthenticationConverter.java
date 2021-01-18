package com.spontzoug.server.auth;

import com.spontzoug.server.redis.ISessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.JaasAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Slf4j
public class ServerTokenAuthenticationConverter implements ServerAuthenticationConverter {
    @Autowired
    private ISessionService sessionService;
    @Override
    public Mono<Authentication> convert (ServerWebExchange serverWebExchange) {

        String token = serverWebExchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        log.info("token converter token:"+token);
        /*
        ArrayList<GrantedAuthority> invalidroles= new ArrayList<>();
        invalidroles.add(new SimpleGrantedAuthority("INVALID"));
        Authentication auth = new UsernamePasswordAuthenticationToken("", "", invalidroles);
        log.info("filter token:" + token);
        log.info("filter ksy:"+ token.substring(7).hashCode());
        if (token.length() > 7) {
            log.info("filter get:");
            return sessionService.getUserSession(token.substring(7))
                    .flatMap(ses -> {
                        log.info("filter not null 11:");
                        ArrayList<GrantedAuthority> roles= new ArrayList<>();
                        for ( String role :ses.getData().getRoles() ) {
                            roles.add(new SimpleGrantedAuthority(role));
                        }
                        log.info("filter not null:");
                        return Mono.just(
                                new UsernamePasswordAuthenticationToken(
                                        ses.getData().getUsername(),
                                        ses.getData().getUsername(),
                                        roles ));
                    });
        } else {
            log.info("filter empty:");
//            return Mono.just( new UsernamePasswordAuthenticationToken("",""));
            return Mono.empty();
        }
    }

         */
        ArrayList<GrantedAuthority> roles= new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("INVALID"));

         Authentication auth = new UsernamePasswordAuthenticationToken(token, token, roles);
            return Mono.just(auth).log();
        }

}