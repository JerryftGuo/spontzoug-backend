package com.spontzoug.server.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeekSchedule {
    private DaySchedule sun;
    private DaySchedule mon;
    private DaySchedule tue;
    private DaySchedule wed;
    private DaySchedule thu;
    private DaySchedule fri;
    private DaySchedule sat;
}