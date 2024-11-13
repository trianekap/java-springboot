package com.codean.topup.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Game {
    private Long idGame;
    @NotBlank(message = "nama game cannot be blank!")
    @Size(min = 5, message = "nama game at least have a 5 character")
    private String namaGame;
    @NotBlank(message = "jenis game cannot be blank!")
    private String jenisGame;
}
