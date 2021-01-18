package com.spontzoug.server.redis;

import com.spontzoug.server.auth.IVerifyCodeService;
import com.spontzoug.server.http.InvitationRequest;
import com.spontzoug.server.http.InvitationResponse;
import com.spontzoug.server.http.InvitationVerifyRequest;
import com.spontzoug.server.http.InvitationVerifyResponse;
import com.spontzoug.server.model.Invitation;
import com.spontzoug.server.model.UserSession;
import com.spontzoug.server.util.ResponseCode;
import com.spontzoug.server.util.SessionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

@Service
public class InvitationService implements IInvitationService{
    @Value("${redis.invitation.expiration}")
    private String invitationexpiration;
    @Autowired
    private IVerifyCodeService verifyCodeService;

    @Autowired
    private ReactiveRedisTemplate<String, Invitation> invitationTemplate;

    @Override
    public Mono<InvitationResponse> create(InvitationRequest request){
        String code = verifyCodeService.generate64();
        Duration timeout = Duration.ofSeconds(Integer.parseInt(invitationexpiration));
        return  Mono.just(new Invitation(request.getBusinessid(),request.getUserid(),request.getRole(), new Date()))
                .flatMap( inv ->{
                    return invitationTemplate
                            .opsForValue()
                            .get(code)
                            .flatMap( c -> {
                                return Mono.just(new InvitationResponse(""));
                            }).switchIfEmpty(
                                invitationTemplate
                                    .opsForValue()
                                    .set(code, inv)
                                    .flatMap( res ->{
                                         return invitationTemplate.expire(code,timeout)
                                                 .flatMap( res2 ->{
                                                     return Mono.just(new InvitationResponse(code));
                                                 });
                                      })
                            );
                });
    }

    @Override
    public Mono<Invitation> find (String invitation){
        String code = invitation.toUpperCase();
            return invitationTemplate
                    .opsForValue()
                    .get(code)
                    .flatMap( res ->{
                        return Mono.just(res);
                    }).switchIfEmpty(
                            Mono.empty()
                    );
    }
}