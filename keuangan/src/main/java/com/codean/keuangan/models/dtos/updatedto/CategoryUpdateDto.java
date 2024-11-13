package com.codean.keuangan.models.dtos.updatedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryUpdateDto {
    @NotBlank(message = "name cannot be blank!")
    private String name;
}
