package com.example.estatechecker.model.entity;

import com.example.estatechecker.FacilityType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Getter
@Setter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "facility")
public class Facility {

    @PrePersist
    public void ensureId() {
        if (id == null) {
            id = UUID.randomUUID();
            log.error("Set UUID after failing");
        }
    }

    @Id
    @Column(name = "uuid", nullable = false)
    private UUID id;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "opening_hours")
    private String openingHours;

    @Enumerated(EnumType.STRING)
    @Column(name = "facility_type", nullable = false)
    private FacilityType facilityType;

    @CreationTimestamp
    @Column(name = "creation_timestamp", updatable = false)
    private OffsetDateTime creationTimestamp;

    @UpdateTimestamp
    @Column(name = "update_timestamp")
    private OffsetDateTime updateTimestamp;
}