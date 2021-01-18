package com.spontzoug.server.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SseEvent {
    private String type; // ent or mesg
    private String bizid; // ent & mesg
    private String stype; // ent
    private String oriid; // mesg
    private String tarid; // mesg
    private String mesg;  // mesg
}