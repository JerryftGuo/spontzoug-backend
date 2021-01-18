package com.spontzoug.server.redis;

import com.spontzoug.server.model.JoinSession;
import com.spontzoug.server.model.TemporarySession;
import com.spontzoug.server.model.UserSession;
import com.spontzoug.server.model.CustomerSession;
import reactor.core.publisher.Mono;

public interface ISessionService {
    Mono<UserSession> getUserSession(final String token);
    Mono<Boolean> setUserSession(final String token,final UserSession session);
    Mono<Boolean> delUserSession(final String token, final String userid);
    Mono<UserSession> getUpdateUserSession(final String token,UserSession session);

    Mono<TemporarySession> getTemporarySession(final String token);
    Mono<Boolean> setTemporarySession(final String token,final TemporarySession session);

    Mono<JoinSession> getJoinSession(final String token);
    Mono<Boolean> setJoinSession(final String token,final JoinSession session);

    Mono<CustomerSession> getCustomerSession(final String token);
    Mono<Boolean> setCustomerSession(final String token,final CustomerSession session);
    Mono<Boolean> delCustomerSession(final String token, final String userid);
}