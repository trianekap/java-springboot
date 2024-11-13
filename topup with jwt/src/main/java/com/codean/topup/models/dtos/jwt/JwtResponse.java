package com.codean.topup.models.dtos.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JwtResponse implements Serializable {
    private String token;
}
