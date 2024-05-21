package com.project.expensetrackingapp.security;

import com.project.expensetrackingapp.exception.JwtException;
import com.project.expensetrackingapp.security.jwt.JwtAuthFilter;
import com.project.expensetrackingapp.security.jwt.JwtService;
import com.project.expensetrackingapp.service.user.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

@SpringBootTest
class JwtAuthFilterTest {

    @InjectMocks
    private JwtAuthFilter jwtAuthFilter;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserDetails userDetails;

    @Mock
    private PrintWriter writer;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoFilterInternalWithValidToken() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn("Bearer validToken");
        when(jwtService.extractUsername("validToken")).thenReturn("testUser");
        when(userDetailsServiceImpl.loadUserByUsername("testUser")).thenReturn(userDetails);
        when(jwtService.validateToken("validToken", userDetails)).thenReturn(true);

        jwtAuthFilter.doFilter(request, response, filterChain);

        verify(jwtService).extractUsername("validToken");
        verify(userDetailsServiceImpl).loadUserByUsername("testUser");
        verify(jwtService).validateToken("validToken", userDetails);
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testDoFilterInternalWithInvalidToken() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn("Bearer invalidToken");
        when(jwtService.extractUsername("invalidToken")).thenThrow(new JwtException("Invalid token", HttpStatus.UNAUTHORIZED));
        when(response.getWriter()).thenReturn(writer);

        jwtAuthFilter.doFilter(request, response, filterChain);

        verify(jwtService).extractUsername("invalidToken");
        verify(response).setStatus(HttpStatus.UNAUTHORIZED.value());
        verify(response.getWriter()).write("Invalid token");
    }

    @Test
    void testDoFilterInternalWithoutToken() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtAuthFilter.doFilter(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testDoFilterInternalWithNullAuthHeader() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtAuthFilter.doFilter(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }


}