package com.example.e_commerce.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "categories")
@RequiredArgsConstructor
public class Categories {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private UUID categoryId;
    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private Set<Products> productsSet = new HashSet<>();

}
