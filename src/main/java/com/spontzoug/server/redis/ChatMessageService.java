package com.spontzoug.server.redis;


import com.spontzoug.server.model.ChatMessage;
import com.spontzoug.server.util.ChatTargetItem;
import com.spontzoug.server.util.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
@Slf4j
@Service
public class ChatMessageService implements IChatMessageService{
    @Value("${chat.message.expiration}")
    private String messageexpiration;

    @Autowired
    private ReactiveRedisTemplate<String, ChatMessage> chatMessageTemplate;

    @Override
    public Flux<Boolean> sendChatMessage( final String id, final ChatMessage message){
        Duration messagetimeout = Duration.ofSeconds(Integer.parseInt(messageexpiration));
        Calendar cal = Calendar.getInstance();
        long mills = cal.getTimeInMillis();
        message.setTimestampe(mills);
        String mykey = RedisConstant.REDIS_CHAT_MEMBER +"."+id;
        return Flux.fromArray(message.getTargets().toArray())
                .flatMap( target ->{
                    String key = ((ChatTargetItem)target).getTargettype() +"."+((ChatTargetItem)target).getId();
                    return chatMessageTemplate
                            .opsForList()
                            .rightPush(key,message)
                            .flatMap( re ->{
                                return chatMessageTemplate.expire(key,messagetimeout);
                                }
                            );
                });
    }

    @Override
    public Flux<ChatMessage> getChatMessage(final String id, final String type){

        String key = type +"."+id;
        ArrayList<ChatMessage> messages = new ArrayList<>();
                chatMessageTemplate
                .opsForList()
                .range(key,0,-1)
                .switchIfEmpty( Flux.empty() )
                .subscribe( e -> messages.add(e));

                chatMessageTemplate
                .opsForList()
                .trim(key, 0,0).subscribe();
        return Flux.fromIterable(messages);
    }
}