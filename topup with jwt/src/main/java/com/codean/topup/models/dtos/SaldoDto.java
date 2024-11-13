package com.codean.topup.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaldoDto {
    private Long idSaldo;

    @NotNull(message = "jenis saldo cannot be null!")
    @NotBlank(message = "jenis saldo cannot be blank!")
    @Pattern(regexp = "[a-z,A-Z]{3,50}" , message = "jenis saldo minimal 3 character!")
    private String jenisSaldo;

    @Min(value = 1000, message = "jumlah saldo minimal 1000!")
    @NotNull(message = "jumlah saldo cannot be null!")
    @JsonProperty("jumSaldo")
    private Long jumlahSaldo;

    @NotNull(message = "id user cannot be null!")
    @Min(value = 1, message = "id user not valid!")
    private Long idUser;
}
