package com.example.e_commerce.model.entities;

import com.example.e_commerce.utilities.ZonedDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "carts")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@RequiredArgsConstructor
public class Carts {

    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private UUID cartId;
    @Min(value = 10000, message = "total price minimal 10000")
    private int totalPrice;


    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime created_at;
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime modified_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(mappedBy = "carts", cascade = CascadeType.ALL)
    private Set<CartItems> cartItems = new HashSet<>();

    @OneToMany(mappedBy = "carts", cascade = CascadeType.ALL)
    private Set<CartsActivityLogEntity> cartsActivityLogEntitySet = new HashSet<>();


}
