package com.codean.topup.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Game {
    private Long idGame;
    private String namaGame;
    private String jenisGame;
}
