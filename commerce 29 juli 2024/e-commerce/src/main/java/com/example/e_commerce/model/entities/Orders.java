package com.example.e_commerce.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private UUID orderId;
    @Email(message = "must be filled in with email format")
    private String email;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "total_amount")
    @Min(value = 0)
    private int totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    @JsonIgnore
    private Payments payments;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private Set<OrderItems> orderItemsSet = new HashSet<>();

    public Orders(UUID orderId, String email, Date orderDate, String orderStatus,
                  int totalAmount){
        this.orderId = orderId;
        this.email = email;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
    }

    public Orders(){

    }
}
