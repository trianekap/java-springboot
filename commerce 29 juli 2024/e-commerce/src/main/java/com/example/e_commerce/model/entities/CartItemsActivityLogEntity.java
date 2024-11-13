package com.example.e_commerce.model.entities;

import com.example.e_commerce.utilities.ZonedDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "cart_items_log")
public class CartItemsActivityLogEntity {

    @Id
    @GeneratedValue
    @Column(name = "cart_items_log_uuid")
    private UUID id;

    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime activityDate;

    private String activityType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_item_uuid")
    private CartItems cartItems;

    @Getter
    @AllArgsConstructor
    public enum ActivityType{
        CREATED_CART_ITEMS("created cart items log"),
        MODIFIED_CART_ITEMS("modified cart items log");
        private final String type;
    }
}
