package com.project.expensetrackingapp.security.jwt;

import com.project.expensetrackingapp.exception.JwtException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    @Value("${app.jwtSecret}")
    private String secretK;

    @Value("${app.jwtExpirationMs}")
    private int expiration;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception ex){
            return handleJwtException(ex);
        }
    }

    private Claims handleJwtException(Exception ex) {
        String errorMessage;
        HttpStatus status;

        if (ex instanceof ExpiredJwtException) {
            errorMessage = "El token JWT ha expirado";
            status = HttpStatus.UNAUTHORIZED;
        } else if (ex instanceof UnsupportedJwtException || ex instanceof MalformedJwtException) {
            errorMessage = "El token JWT es inválido o no soportado";
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof SignatureException) {
            errorMessage = "Error de firma en el token JWT";
            status = HttpStatus.UNAUTHORIZED;
        } else if (ex instanceof IllegalArgumentException) {
            errorMessage = "El token JWT es inválido";
            status = HttpStatus.BAD_REQUEST;
        } else {
            errorMessage = "Error al procesar el token JWT";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        throw new JwtException(errorMessage, status);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String GenerateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        Instant now = Instant.now();
        Instant expirationInstant = now.plus(Duration.ofMillis(expiration));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expirationInstant))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretK);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

