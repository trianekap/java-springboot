package com.example.e_commerce.utilities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    private static final Logger logger = Logger.getLogger(String.valueOf(JwtTokenUtil.class));
    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_Token_Validity = 15*60*60;
    @Value("${jwt.secret}")
    private String secret;
    private String role;
    public Date getIssuedAtDateFromToken(String token){
        return  getClaimFromToken(token, Claims::getIssuedAt);
    }
    //parsing username dr token
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //parsing token
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    //generate token berdasar informasi pengguna(dari UserDetails)
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        logger.info(String.valueOf(claims));
        return doGenerateToken(claims, userDetails.getUsername());
    }
    //buat token
    private String doGenerateToken(Map<String, Object> claims, String subject){
        logger.info(String.valueOf(claims));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_Token_Validity*1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }
    //validasi token apakah msh valid untuk pengguna tertentu
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    //parsing tanggal kedaluwarsa token
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }
    //periksa token sudah kedaluwarsa/blm
    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    private Boolean ignoreTokenExpiration(String token){
        return false;
    }
    //periksa apa token dpaat diperbarui
    public Boolean canTokenBeRefreshed(String token){
        return (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }
}
