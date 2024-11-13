package com.example.e_commerce.model.entities;

import com.example.e_commerce.utilities.ZonedDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "products_log")
public class ProductsActivityLogEntity {
    @Id
    @GeneratedValue
    @Column(name = "products_log_uuid")
    private UUID id;

    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime activityDate;

    private String activityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_uuid")
    @JsonIgnore
    private Products products;

    @Getter
    @AllArgsConstructor
    public enum ActivityType{
        CREATED_PRODUCTS("created products log"),
        MODIFIED_PRODUCTS("modified products log");
        private final String type;
    }
}
