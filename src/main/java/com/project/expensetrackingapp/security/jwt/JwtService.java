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

/**
 * Manage JWT Service to manage claims and token data
 * @author Angel Sic
 */
@Component
public class JwtService {

    @Value("${app.jwtSecret}")
    private String secretK;

    @Value("${app.jwtExpirationMs}")
    private int expiration;

    /**
     * Extract Username form Token
     * @param token Token access
     * @return Username
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extract Expiration Date
     * @param token Token access
     * @return Date expiration
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extract Claims from Token
     * @param token Token access
     * @param claimsResolver Claims Resolver
     * @return Claims Data
     * @param <T> Object
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extract all Claims method
     * @param token Token access
     * @return Claims data
     */
    public Claims extractAllClaims(String token) {
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

    /**
     * Custom Handler JWT Exception
     * @param ex Type of Exception
     * @return Throw Exception
     */
    public Claims handleJwtException(Exception ex) {
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

    /**
     * Validate if token is expired
     * @param token Token access
     * @return Boolean if token is expired or not
     */
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Validate Token
     * @param token Token access
     * @param userDetails User Details Data
     * @return Boolean if token is correct or not
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Map token generation
     * @param username Username data
     * @return Get back Token
     */
    public String GenerateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    /**
     * Create Token with Jwts builder
     * @param claims Claims data
     * @param username Username data
     * @return String Token access
     */
    public String createToken(Map<String, Object> claims, String username) {
        Instant now = Instant.now();
        Instant expirationInstant = now.plus(Duration.ofMillis(expiration));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expirationInstant))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    /**
     * Generate Sign from Key configuration
     * @return Get back Sign key
     */
    public Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretK);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

