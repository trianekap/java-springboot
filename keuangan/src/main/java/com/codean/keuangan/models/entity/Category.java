package com.codean.keuangan.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private int id;
    @NotBlank(message = "name cannot be blank!")
    private String name;
}
