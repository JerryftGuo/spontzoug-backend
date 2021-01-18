package com.spontzoug.server.config;

import com.spontzoug.server.model.*;
import com.spontzoug.server.util.SseEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.data.redis.serializer.*;

@Configuration
public class RedisConfig {
    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private String port;

    @Bean
    @Primary
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(){
        return new LettuceConnectionFactory(host, Integer.parseInt(port));
    }

    @Bean
    ReactiveRedisTemplate<String, UserSession>
        reactiveRedisUserSessionTemplate(ReactiveRedisConnectionFactory factory){
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<UserSession> valueSerializer =
                    new Jackson2JsonRedisSerializer<UserSession>(UserSession.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, UserSession> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);
        RedisSerializationContext<String, UserSession> context =
                builder
                        .value(valueSerializer)
                        .hashKey(keySerializer)
                        .hashValue(valueSerializer)
                        .build();

        return  new ReactiveRedisTemplate<>(factory,context);
    }

    @Bean
    ReactiveRedisTemplate<String, CustomerSession>
    reactiveRedisCustomerSessionTemplate(ReactiveRedisConnectionFactory factory){
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<CustomerSession> valueSerializer =
                new Jackson2JsonRedisSerializer<>(CustomerSession.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, CustomerSession> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);
        RedisSerializationContext<String, CustomerSession> context =
                builder
                        .value(valueSerializer)
                        .hashKey(keySerializer)
                        .hashValue(valueSerializer)
                        .build();

        return  new ReactiveRedisTemplate<>(factory,context);
    }

    @Bean
    ReactiveRedisTemplate<String, TemporarySession>
    reactiveRedisTemporarySessionTemplate(ReactiveRedisConnectionFactory factory){
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<TemporarySession> valueSerializer =
                new Jackson2JsonRedisSerializer<TemporarySession>(TemporarySession.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, TemporarySession> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);
        RedisSerializationContext<String, TemporarySession> context =
                builder
                        .value(valueSerializer)
                        .hashKey(keySerializer)
                        .hashValue(valueSerializer)
                        .build();

        return  new ReactiveRedisTemplate<>(factory,context);
    }
    @Bean
    ReactiveRedisTemplate<String, JoinSession>
    reactiveRedisJoinSessionTemplate(ReactiveRedisConnectionFactory factory){
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<JoinSession> valueSerializer =
                new Jackson2JsonRedisSerializer<JoinSession>(JoinSession.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, JoinSession> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);
        RedisSerializationContext<String, JoinSession> context =
                builder
                        .value(valueSerializer)
                        .hashKey(keySerializer)
                        .hashValue(valueSerializer)
                        .build();

        return  new ReactiveRedisTemplate<>(factory,context);
    }
    @Bean
    ReactiveRedisTemplate<String, CodeVerifyItem>
    reactiveRedisCodeVerifyItemSessionTemplate(ReactiveRedisConnectionFactory factory){
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<CodeVerifyItem> valueSerializer =
                new Jackson2JsonRedisSerializer<CodeVerifyItem>(CodeVerifyItem.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, CodeVerifyItem> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);
        RedisSerializationContext<String, CodeVerifyItem> context =
                builder
                        .value(valueSerializer)
                        .build();

        return  new ReactiveRedisTemplate<>(factory,context);
    }

    @Bean
    ReactiveRedisTemplate<String, Invitation>
    reactiveRedisSignupSessoinTemplate(ReactiveRedisConnectionFactory factory){
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Invitation> valueSerializer =
                new Jackson2JsonRedisSerializer<>(Invitation.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, Invitation> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);
        RedisSerializationContext<String, Invitation> context =
                builder
                        .value(valueSerializer)
                        .build();
        return  new ReactiveRedisTemplate<>(factory,context);
    }

// sse publisher and subscriber
    @Bean
    ReactiveRedisTemplate<String, SseEvent>
    reactiveRedisSseEventTemplate(ReactiveRedisConnectionFactory factory){
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<SseEvent> valueSerializer =
                new Jackson2JsonRedisSerializer<>(SseEvent.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, SseEvent> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);
        RedisSerializationContext<String, SseEvent> context =
                builder
                        .value(valueSerializer)
                        .build();
        return  new ReactiveRedisTemplate<>(factory,context);
    }

    // chat message
    @Bean
    ReactiveRedisTemplate<String, ChatMessage>
    reactiveRedisChatMessageTemplate(ReactiveRedisConnectionFactory factory){
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<ChatMessage> valueSerializer =
                new Jackson2JsonRedisSerializer<>(ChatMessage.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, ChatMessage> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);
        RedisSerializationContext<String, ChatMessage> context =
                builder
                        .value(valueSerializer)
                        .build();
        return  new ReactiveRedisTemplate<>(factory,context);
    }
/*
    @Bean
    ChannelTopic topic(String channel){
        return new ChannelTopic((channel));
    }

    @Bean
    ReactiveRedisMessageListenerContainer reactiveRedisMessageListenerContainer(){
        return new ReactiveRedisMessageListenerContainer(reactiveRedisConnectionFactory());
    }

 */
}