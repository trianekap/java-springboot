package com.example.e_commerce.model.entities;


import com.example.e_commerce.utilities.ZonedDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "user_log")
public class UsersActivityLogEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime activityDate;

    private String activityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid")
    @JsonIgnore
    private Users users2;

    @Getter
    @AllArgsConstructor
    public enum ActivityType{
        CREATED_USER("created user log"),
        MODIFIED_USER("modified user log");
        private final String type;
    }
}
