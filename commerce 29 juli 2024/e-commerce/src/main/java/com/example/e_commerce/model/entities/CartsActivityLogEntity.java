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
@Table(name = "carts_log")
public class CartsActivityLogEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime activityDate;

    private String activityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_uuid")
    private Carts carts;

    @Getter
    @AllArgsConstructor
    public enum ActivityType{
        CREATED_CARTS("created cart log"),
        MODIFIED_CARTS("modified cart log");
        private final String type;
    }
}
