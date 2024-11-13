package com.example.e_commerce.model.entities;

import com.example.e_commerce.model.dtos.OrdersGetDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "payments")
public class Payments {

    @Id
    @GeneratedValue
    @Column(name = "payment_id")
    private UUID paymentId;
    @Column(name = "payment_method")
    private String paymentMethod;

    @OneToMany(mappedBy = "payments", cascade = CascadeType.ALL)
    private Set<Orders> orders = new HashSet<>();

    public Payments(UUID paymentId, String paymentMethod){
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
    }

    public Payments(){

    }
}
