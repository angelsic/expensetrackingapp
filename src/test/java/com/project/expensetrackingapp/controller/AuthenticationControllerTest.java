package com.project.expensetrackingapp.controller;

import com.project.expensetrackingapp.controller.AuthenticationController;
import com.project.expensetrackingapp.exception.auth.InvalidPasswordException;
import com.project.expensetrackingapp.exception.user.UsernameNotFound;
import com.project.expensetrackingapp.repository.entity.auth.AuthRequestDTO;
import com.project.expensetrackingapp.repository.entity.auth.JwtResponseDTO;
import com.project.expensetrackingapp.repository.entity.user.UserRequest;
import com.project.expensetrackingapp.repository.entity.user.UserResponse;
import com.project.expensetrackingapp.security.jwt.JwtService;
import com.project.expensetrackingapp.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    void testSaveUser(){
        UserRequest userRequest = new UserRequest();
        UserResponse userResponse = new UserResponse();

        when(userService.saveUser(userRequest)).thenReturn(userResponse);

        ResponseEntity<UserResponse> responseEntity = authenticationController.saveUser(userRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse, responseEntity.getBody());
    }

    @Test
    void testAuthenticationAndGetToken() {
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        String username = "testUser";
        String token = "testToken";
        authRequestDTO.setUsername(username);

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(jwtService.GenerateToken(username)).thenReturn(token);
        when(jwtService.extractExpiration(token)).thenReturn(new Date());

        JwtResponseDTO jwtResponseDTO = authenticationController.AuthenticationAndGetToken(authRequestDTO);

        assertNotNull(jwtResponseDTO);
        assertEquals(token, jwtResponseDTO.getAccessToken());
    }

    @Test
    void testAuthenticationInvalidPassword() {
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        authRequestDTO.setUsername("testUser");
        authRequestDTO.setPassword("invalidPassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new AuthenticationException("") {
                    @Override
                    public String getMessage() {
                        return super.getMessage();
                    }
                });
        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> {
            JwtResponseDTO jwtResponseDTO = authenticationController.AuthenticationAndGetToken(authRequestDTO);
        });
    }

    @Test
    void testAuthenticationUsernameNotFound() {
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        String username = "testUser";
        authRequestDTO.setUsername(username);

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(false);

        UsernameNotFound exception = assertThrows(UsernameNotFound.class, () -> {
            JwtResponseDTO jwtResponseDTO = authenticationController.AuthenticationAndGetToken(authRequestDTO);
        });

    }

}
