package com.spontzoug.server.redis;

import com.spontzoug.server.model.ChatMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IChatMessageService {
    Flux<ChatMessage> getChatMessage(final String id, final String type);
    Flux<Boolean> sendChatMessage(final String senderid,final ChatMessage message);

}