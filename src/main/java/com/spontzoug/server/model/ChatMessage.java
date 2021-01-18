package com.spontzoug.server.model;

import com.spontzoug.server.util.ChatTargetItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String source;
    private Long  timestampe;
    private ArrayList<ChatTargetItem> targets;
    private String type;   // text or object
    private String content;
}