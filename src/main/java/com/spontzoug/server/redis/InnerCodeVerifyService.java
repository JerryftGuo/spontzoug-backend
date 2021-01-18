package com.spontzoug.server.redis;

import com.spontzoug.server.auth.ISignatureService;
import com.spontzoug.server.auth.IVerifyCodeService;
import com.spontzoug.server.http.InnerCodeVerifyRequest;
import com.spontzoug.server.http.InnerCodeVerifyResponse;
import com.spontzoug.server.model.CodeVerifyItem;
import com.spontzoug.server.model.TemporarySession;
import com.spontzoug.server.model.UserSession;
import com.spontzoug.server.util.CodeVerifyUtil;
import com.spontzoug.server.util.RedisConstant;
import com.spontzoug.server.util.SessionConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;
@Slf4j
@Service
public class InnerCodeVerifyService implements IInnerCodeVerifyService{
    @Value("${redis.code.expiration}")
    private String codeexpiration;

    @Autowired
    private ISignatureService signatureService;
    @Autowired
    private IVerifyCodeService verifyCodeService;

    @Autowired
    private ReactiveRedisTemplate<String, CodeVerifyItem> codeTemplate;

    @Override
    public Mono<Boolean> verify(String key, String code){
        log.info("inner code verify: key="+key +" code:"+code);
        return  codeTemplate
                .opsForValue()
                .get(key)
                .flatMap( itm ->{
                    log.info("item: code="+itm.getData().getCode());
                    return signatureService.verify(itm)
                            .flatMap( it -> {
                                log.info("verify: key="+it.getData().getCode());
                                if( it.getData().getCode().equals(code.toUpperCase()))
                                    return Mono.just(true);
                                else
                                    return Mono.just(false);
                            }).defaultIfEmpty( false);
                }).defaultIfEmpty( false);
    }

    @Override
    public Mono<InnerCodeVerifyResponse> create( InnerCodeVerifyRequest request){
        String codec = verifyCodeService.generate32();
        log.info("text code:"+codec);
        Duration codetimeout = Duration.ofSeconds(Integer.parseInt(codeexpiration));
        String name = request.getName();
        String type = request.getType();
        String key = RedisConstant.REDIS_CODE +"."+name;
        CodeVerifyItem item = new CodeVerifyItem( new CodeVerifyUtil(
            type, name, codec,new Date()
            ),"sign");
        return  signatureService.sign(item)
                .flatMap( itm ->{
                    return codeTemplate
                            .opsForValue()
                            .set(key, itm)
                            .flatMap( res ->{
                                    return codeTemplate.expire(key,codetimeout);
                            }).flatMap(
                                    res1 ->{
                                        return Mono.just( new InnerCodeVerifyResponse(type, key));
                                    }
                            );
                });
    }

}