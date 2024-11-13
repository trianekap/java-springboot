package com.example.e_commerce.model.entities;

import com.example.e_commerce.utilities.ZonedDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "cart_items")
public class CartItems {

    @Id
    @GeneratedValue
    @Column(name = "cart_items_id")
    private UUID cartItemId;
    private int discount;
    @Column(name = "product_price")
    private int productPrice;
    @NotNull
    @Min(value = 1, message = "at least quantity is 1")
    private int quantity;

    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime createdAt;
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime modified_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Carts carts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Products products;

}
