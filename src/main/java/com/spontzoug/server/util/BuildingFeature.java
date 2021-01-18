package com.spontzoug.server.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingFeature {
    private String features;
    private String foundationtype;
    private String style;
    private String architecturestyle;
    private String constructionmaterial;
    private String floorspace;
    private String finishedspace;
}