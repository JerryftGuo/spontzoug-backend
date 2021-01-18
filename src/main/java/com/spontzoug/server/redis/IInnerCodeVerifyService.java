package com.spontzoug.server.redis;

import com.spontzoug.server.http.InnerCodeVerifyRequest;
import com.spontzoug.server.http.InnerCodeVerifyResponse;
import reactor.core.publisher.Mono;

public interface IInnerCodeVerifyService {
    Mono<InnerCodeVerifyResponse> create(InnerCodeVerifyRequest request);
    Mono<Boolean> verify(String name, String code);
}