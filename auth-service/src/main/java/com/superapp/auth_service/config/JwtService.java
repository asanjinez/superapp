package com.superapp.auth_service.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    //Todo posiblemente este mal generado el secret
    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expirationTime.hours}")
    private Integer expirationTime;
    public String extractUsername(String jwt) {
        return this.extractClaim(jwt, Claims::getSubject);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(this.getSigningKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimResolver) {
        final Claims claims = this.extractAllClaims(jwtToken);
        return claimResolver.apply(claims);
    }

    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * expirationTime))
                .signWith(this.getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(UserDetails userDetails){
        return this.generateToken(new HashMap<>(), userDetails);
    }

    public Boolean validateToken(String jwtToken, UserDetails userDetails) {
        final String username = this.extractUsername(jwtToken);
        return username.equals(userDetails.getUsername()) && !this.isTokenExpired(jwtToken);
    }

    public Boolean isTokenExpired(String jwtToken) {
        return this.extractExpiration(jwtToken).before(new java.util.Date());
    }

    public Date extractExpiration(String jwtToken) {
        return this.extractClaim(jwtToken, Claims::getExpiration);
    }
}
