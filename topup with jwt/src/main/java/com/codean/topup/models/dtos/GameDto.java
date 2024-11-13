package com.codean.topup.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {
    private Long idGame;
    @NotBlank(message = "nama game cannot be blank!")
    @Size(min = 5, message = "nama game at least have a 5 character")
    private String namaGame;
    @NotBlank(message = "jenis game cannot be blank!")
    private String jenisGame;
}
