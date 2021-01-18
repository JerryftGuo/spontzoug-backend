package com.spontzoug.server.util;


import com.mongodb.client.model.geojson.Point;
//import org.springframework.data.geo.Point;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String city;
    private String province;
    private String postcode;
    private GeoPoint geolocation;

}