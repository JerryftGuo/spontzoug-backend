package com.spontzoug.server.annotation;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole(T(com.spontzoug.server.util.Roles).RL_ENT_SIGNIN)")
public  @interface IsUserAuthenticationEntSignin {}