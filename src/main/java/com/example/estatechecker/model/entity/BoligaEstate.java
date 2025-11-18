package com.example.estatechecker.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Getter
@Setter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "boliga_estates")
public class BoligaEstate {

    @PrePersist
    public void ensureId() {
        if (id == null) {
            // TODO: make prom metrics and grafnaa alerts of failing ids
            id = UUID.randomUUID();
            log.error("UUID is should not happen as we set the guid of an estate. Id failing: {}", id);
        }
    }

    @Id
    @Comment("This is the guid of a boliga estate")
    @Column(name = "uuid", nullable = false)
    private UUID id;

    @Column(name = "address")
    private String address;
    @Column(name = "zip_code")
    private int zipCode;

    @Column(name = "price")
    private int price;

    @Column(name = "sold_date")
    private OffsetDateTime soldDate;

    @Column(name = "property_type")
    private int propertyType;

    @Column(name = "sale_type")
    private String saleType;

    @Column(name = "sqm_price")
    private double sqmPrice;

    @Column(name = "rooms")
    private Integer rooms;

    @Column(name = "size")
    private Integer size;

    @Column(name = "build_year")
    private Integer buildYear;

    @Column(name = "change_value")
    private double change;

    @Column(name = "guid")
    private String guid;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "municipality_code")
    private Integer municipalityCode;

    @Column(name = "estate_code")
    private Integer estateCode;

    @Column(name = "city")
    private String city;

    @Column(name = "group_key")
    private String groupKey;

    @Column(name = "can_get_vr")
    private Boolean canGetVR;

    @Column(name = "bf_enr")
    private Long bfEnr;

    @Column(name = "ou_id")
    private Long ouId;

    @Column(name = "ou_address")
    private String ouAddress;

    @CreationTimestamp
    @Column(name = "creation_timestamp", updatable = false)
    private OffsetDateTime creationTimestamp;

    @UpdateTimestamp
    @Column(name = "update_timestamp")
    private OffsetDateTime updateTimestamp;



}
