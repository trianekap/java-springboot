package com.codean.topup.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MataUang {
    private Long idMataUang;
    @NotBlank(message = "nama mata uang cannot be blank!")
    @Min(value = 3)
    private String namaMataUang;
}
