package com.arfaoui.springSecurityApp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public class JwtService {
    private static final String SECRET_KEY="46294A404D635166546A576E5A7234753778214125442A472D4B615064526755" ;

    public String generateToken(Map<String,Object> extractClaims , UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact() ;
    }
    public String extractUsername(String token){
        return extractClaim(token , Claims::getSubject) ;
    }

    public <T> T extractClaim(String token , Function<Claims,T> claimResolver){
        final Claims claims = extractAllClaims(token) ;
        return claimResolver.apply(claims) ;
    }
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody() ;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY) ;
        return Keys.hmacShaKeyFor(keyBytes) ;
    }

}
