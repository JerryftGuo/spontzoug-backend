package com.spontzoug.server.auth;

import com.spontzoug.server.model.*;
import reactor.core.publisher.Mono;

public interface ISignatureService {
    Mono<UserSession> sign(UserSession sessione);
    Mono<CustomerSession> sign(CustomerSession sessione);
    Mono<TemporarySession> sign(TemporarySession session);
    Mono<JoinSession> sign(JoinSession session);
    Mono<CodeVerifyItem> sign(CodeVerifyItem code);
    Mono<UserSession> verify(UserSession session);
    Mono<CustomerSession> verify(CustomerSession session);
    Mono<TemporarySession> verify(TemporarySession session);
    Mono<JoinSession> verify(JoinSession session);
    Mono<CodeVerifyItem> verify(CodeVerifyItem code);

}