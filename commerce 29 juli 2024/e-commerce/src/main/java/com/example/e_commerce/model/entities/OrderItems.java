package com.example.e_commerce.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "order_items")
public class OrderItems {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private UUID orderItemId;
    @Min(value = 1000)
    private int discount;
    @Min(value = 0)
    @Column(name = "ordered_product_price")
    private int orderedProductPrice;
    @Min(value = 0)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Products products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Orders orders;

    public OrderItems(UUID orderItemId, int discount, int orderedProductPrice,
                      int quantity){
        this.orderItemId = orderItemId;
        this.discount = discount;
        this.orderedProductPrice = orderedProductPrice;
        this.quantity = quantity;
    }

    public OrderItems(){

    }
}
