package com.codean.topup.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaketTopupDto {
    private Long idPaket;
    @NotBlank
    @Min(value = 5)
    private String namaPaket;
    @Min(value = 1)
    @NotNull(message = "id game cannot be null!")
    private Long idGame;
    @Min(value = 1)
    @NotNull(message = "jumlah diamond cannot be null!")
    private Long jumlahDiamond;
    @NotNull(message = "harga cannot be null!")
    @Min(value = 1000)
    private Long harga;
    @NotNull(message = "bonus cannot be null")
    private Long bonus;
}
