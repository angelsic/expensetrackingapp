package com.project.expensetrackingapp.security;

import com.project.expensetrackingapp.exception.JwtException;
import com.project.expensetrackingapp.security.jwt.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JwtServiceTest {

    @InjectMocks
    JwtService jwtService;

    @Mock
    UserDetails userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(jwtService, "secretK", "==============================test=secret=key=value===================");
        ReflectionTestUtils.setField(jwtService, "expiration", 3600000);
    }

    @Test
    void testGenerateToken() {
        String username = "testUser";
        String token = jwtService.GenerateToken(username);
        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    void testExtractUsername() {
        String username = "testUser";
        String token = jwtService.GenerateToken(username);
        assertEquals(username, jwtService.extractUsername(token));
    }

    @Test
    void testExtractExpiration() {
        String username = "testUser";
        String token = jwtService.GenerateToken(username);
        Date expiration = jwtService.extractExpiration(token);
        assertNotNull(expiration);
    }

    @Test
    void testValidateToken() {
        String username = "testUser";
        String token = jwtService.GenerateToken(username);

        when(userDetails.getUsername()).thenReturn(username);
        assertTrue(jwtService.validateToken(token, userDetails));
    }

    @Test
    void testValidateTokenExpired() {
        String username = "testUser";
        ReflectionTestUtils.setField(jwtService, "expiration", -1000);
        String expiredToken = generateExpiredToken(username);

        when(userDetails.getUsername()).thenReturn(username);
        try {
            assertFalse(jwtService.validateToken(expiredToken, userDetails));
        } catch (JwtException e) {
            assertEquals("El token JWT ha expirado", e.getMessage());
        }
    }

    @Test
    void testExtractAllClaimsWithInvalidToken() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(jwtService, "secretK", "==============================test=secret=key=value===================");
        String invalidToken = "invalid_token";

        try {
            jwtService.extractAllClaims(invalidToken);
            fail("Se esperaba que lanzara una excepción JwtException");
        } catch (JwtException e) {
            assertEquals("El token JWT es inválido o no soportado", e.getMessage());
        }
    }

    @Test
    void testHandleJwtExceptionExpiredJwtException() {
        JwtService jwtService = new JwtService();
        Exception ex = new ExpiredJwtException(null, null, "Expired JWT");
        try {
            jwtService.handleJwtException(ex);
            fail("Se esperaba que lanzara una excepción JwtException");
        } catch (JwtException e) {
            assertEquals("El token JWT ha expirado", e.getMessage());
            assertEquals(HttpStatus.UNAUTHORIZED, e.getStatus());
        }
    }

    @Test
    void testHandleJwtExceptionUnsupportedJwtException() {
        JwtService jwtService = new JwtService();
        Exception ex = new UnsupportedJwtException("Unsupported JWT");
        try {
            jwtService.handleJwtException(ex);
            fail("Se esperaba que lanzara una excepción JwtException");
        } catch (JwtException e) {
            assertEquals("El token JWT es inválido o no soportado", e.getMessage());
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
        }
    }

    @Test
    void testHandleJwtExceptionSignatureException() {
        JwtService jwtService = new JwtService();
        Exception ex = new SignatureException("Signature Error");
        try {
            jwtService.handleJwtException(ex);
            fail("Se esperaba que lanzara una excepción JwtException");
        } catch (JwtException e) {
            assertEquals("Error de firma en el token JWT", e.getMessage());
            assertEquals(HttpStatus.UNAUTHORIZED, e.getStatus());
        }
    }

    @Test
    void testHandleJwtExceptionIllegalArgumentException() {
        JwtService jwtService = new JwtService();
        Exception ex = new IllegalArgumentException("Illegal Argument");
        try {
            jwtService.handleJwtException(ex);
            fail("Se esperaba que lanzara una excepción JwtException");
        } catch (JwtException e) {
            assertEquals("El token JWT es inválido", e.getMessage());
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
        }
    }

    @Test
    void testHandleJwtExceptionInternalServerError() {
        JwtService jwtService = new JwtService();
        Exception ex = new Exception("Internal Server Error");
        try {
            jwtService.handleJwtException(ex);
            fail("Se esperaba que lanzara una excepción JwtException");
        } catch (JwtException e) {
            assertEquals("Error al procesar el token JWT", e.getMessage());
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getStatus());
        }
    }

    private String generateExpiredToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return jwtService.createToken(claims, username);
    }
}
