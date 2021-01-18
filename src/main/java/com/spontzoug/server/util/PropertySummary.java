package com.spontzoug.server.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertySummary {
    private String propertytype;
    private String buildingtype;
    private String title;
    private String landsize;
    private String builtyear;
    private String annaultax;
    private String parkingtype;
}