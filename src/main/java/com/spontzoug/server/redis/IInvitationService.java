package com.spontzoug.server.redis;

import com.spontzoug.server.http.InvitationRequest;
import com.spontzoug.server.http.InvitationResponse;
import com.spontzoug.server.http.InvitationVerifyRequest;
import com.spontzoug.server.http.InvitationVerifyResponse;
import com.spontzoug.server.model.Invitation;
import reactor.core.publisher.Mono;

public interface IInvitationService {
    Mono<InvitationResponse> create(InvitationRequest request);
    Mono<Invitation> find(String invitation);
}