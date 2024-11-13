package com.codean.topup.models.entity;


import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bayar {
    private Long idBayar;
    @NotNull(message = "id paket cannot be null!")
    @Min(value = 1)
    private Long idPaket;
    @NotNull(message = "id method cannot be null!")
    @Min(value = 1)
    private Long idMethod;
    @NotNull(message = "id user cannot be null!")
    @Min(value = 1)
    private Long idUser;
    private Long totalBayar;
    @NotNull(message = "id saldo cannot be null!")
    @Min(value = 1)
    private Long idSaldo;
}
