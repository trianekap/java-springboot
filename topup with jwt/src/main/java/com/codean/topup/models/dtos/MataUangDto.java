package com.codean.topup.models.dtos;

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
public class MataUangDto {
    private Long idMataUang;
    @NotBlank(message = "nama mata uang cannot be blank!")
    @Min(value = 3)
    private String namaMataUang;
}