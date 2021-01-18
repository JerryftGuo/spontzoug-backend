package com.spontzoug.server.model;

import com.spontzoug.server.util.Address;
import com.spontzoug.server.util.ImageItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "business")
@Data
@NoArgsConstructor
@AllArgsConstructor
@CompoundIndexes({
        @CompoundIndex(name="province_city_name", def="{'address.province':1, 'address.city':1, 'name':1}"),
        @CompoundIndex(name="province_city_subtype", def="{'address.province':1, 'address.city':1, 'subtype':1}"),
})
public class Business {
    @Id
    private String id;
    @TextIndexed
    private String businessname;
    private String companyname;
    private String outline;
    private String industry;
    @TextIndexed
    private String subtype;
    private String description;
    private String referencecode;
    private Address address;
    private List<ImageItem> background;
    private List<ImageItem> logo;
    private Integer billingday;
    private Boolean closed;
    private Boolean isvip;
    private String creator;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date modified;
}
