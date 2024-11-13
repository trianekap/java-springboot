package com.example.e_commerce.model.entities;

import com.example.e_commerce.utilities.ZonedDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private UUID productId;

    @NotBlank
    @Length(min = 10)
    private String description;
    private int discount;
    private String image;
    @Min(value = 10000)
    private int price;
    @NotBlank
    @Length(min = 5)
    private String productName;
    @Min(value = 1)
    private int quantity;
    @Min(value = 10000)
    private int specialPrice;

    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime created_at;

    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime modified_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Categories categories;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private Set<OrderItems> orderItemsSet = new HashSet<>();

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private Set<CartItems> cartItemsSet = new HashSet<>();

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private Set<ProductsActivityLogEntity> productsActivityLogEntitySet = new HashSet<>();


}
