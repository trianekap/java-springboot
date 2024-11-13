package com.codean.topup.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    public static final long JWT_TOKENVALIDITY = 3*60*60*1000;

    @Value("${secret}")
    private String secret;

    //mendapatkan user dari token
    public String getUserFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    //mendapatkan waktu kadaluwarsa token
    public Date getExpirationDate(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //mendapatkan issue dari token
    public Date atIssuedAt(String token){
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //memeriksa apakah token sudah kadaluwarsa
    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDate(token);
        return expiration.before(new Date());
    }


    private Boolean ignoredTokenExpired(String token){
        return false;
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return null;
    }

    //untuk megenerate token
    public String doGenerateToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKENVALIDITY))
                .signWith(SignatureAlgorithm.RS256, secret).compact();
    }

    public Boolean canTokenBeRefreshed(String token){
        return !isTokenExpired(token) || ignoredTokenExpired(token);
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUserFromToken(token);
        return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
