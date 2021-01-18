package com.spontzoug.server.config;

import com.spontzoug.server.auth.ServerTokenAuthenticationConverter;
import com.spontzoug.server.auth.ServerTokenAuthenticationManager;
import com.spontzoug.server.redis.ISessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

import java.security.SecureRandom;

@Slf4j
@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {
    @Autowired
    ISessionService sessionService;

    @Bean
    public PasswordEncoder passwordEncoder(){
          return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecureRandom secureRandom() { return new SecureRandom();}

    @Bean
    public MapReactiveUserDetailsService userDetailsService(){
        UserDetails user = User
                .withUsername("user")
                .password( "{bcrypt}$2a$10$20F/rAI/09CqpSVaPUCgP.EjRZa/xl0zdwNJK505BEM39DiNbwoSO")
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService((user));
    }


    @Bean
    AuthenticationWebFilter tokenAuthenticationFilter(){

        log.info("token filter");
        ServerTokenAuthenticationManager serverTokenAuthenticationManager = new ServerTokenAuthenticationManager(sessionService);
        ServerTokenAuthenticationConverter serverTokenAuthenticationConverter = new ServerTokenAuthenticationConverter();
        AuthenticationWebFilter filter = new AuthenticationWebFilter(serverTokenAuthenticationManager);
        log.info("token 2");
        filter.setServerAuthenticationConverter(serverTokenAuthenticationConverter);
        filter.setRequiresAuthenticationMatcher(
                ServerWebExchangeMatchers.pathMatchers("/businesssignup/business",
                        "/businesssignup/basicconfig",
                        "/businesssignup/userprofile",
                        "/businesssignup/paymentmethod",
                        "/businesssignup/innercodeverify",
                        "/businessjoin/innercodeverify",
                        "/businessjoin/basicconfig",
                        "/businessjoin/userprofile",
                        "/auth/user/signout",
                        "/auth/user/changepassword",
                        "/auth/customer/signout",
                        "/auth/customer/changepassword",
                        "/api/**")
        );
        log.info("token 3");
        return filter;
    }



    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        http
                .authorizeExchange()
                .pathMatchers("/businesssignup/userverify",
                        "/businesssignup/signup",
                        "/businesssignup/codeverify",
                        "/businessjoin/invitationverify",
                        "/businessjoin/userverify",
                        "/businessjoin/join",
                        "/businessjoin/codeverify",
                        "/customersignup/signup",
                        "/customersignup/userverify",
                        "/customersignup/codeverify",
                        "/auth/user/signin",
                        "/auth/user/codeverify",
                        "/auth/user/verifypasswordemail",
                        "/auth/user/resetpassword",
                        "/auth/customer/signin",
                        "/auth/customer/codeverify",
                        "/auth/customer/verifypasswordemail",
                        "/auth/customer/resetpassword",
                        "/photo/**",
                        "/sse/**",
                        "/auth/usercodeverify").permitAll()
                .and()
                .addFilterAt(tokenAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                .authorizeExchange()
                .pathMatchers("/businesssignup/business",
                        "/businesssignup/basicconfig",
                        "/businesssignup/userprofile",
                        "/businesssignup/paymentmethod",
                        "/businesssignup/innercodeverify",
                        "/businessjoin/innercodeverify",
                        "/businessjoin/basicconfig",
                        "/businessjoin/userprofile",
                        "/auth/user/signout",
                        "/auth/user/changepassword",
                        "/auth/customer/signout",
                        "/auth/customer/changepassword",
                        "/api/**")
                .authenticated()
                .and()
                .csrf().disable()
                .httpBasic().disable()
                .logout().disable();
        return  http.build();
    }


}