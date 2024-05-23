package com.project.expensetrackingapp.controller;

import com.project.expensetrackingapp.exception.auth.InvalidPasswordException;
import com.project.expensetrackingapp.exception.user.UsernameNotFound;
import com.project.expensetrackingapp.repository.entity.auth.AuthRequestDTO;
import com.project.expensetrackingapp.repository.entity.auth.JwtResponseDTO;
import com.project.expensetrackingapp.repository.entity.user.UserRequest;
import com.project.expensetrackingapp.repository.entity.user.UserResponse;
import com.project.expensetrackingapp.security.jwt.JwtService;
import com.project.expensetrackingapp.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

/**
 * Authentication Controller
 * Base url: /api/v1
 * Use for login and sign as new user application
 * @author Angel Sic
 */
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Authentication Process")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    private JwtService jwtService;

    @Value("${app.jwtExpirationMs}")
    private int expiration;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Allows to sign up as new user application
     * Url: /signup
     * @param userRequest UserRequest structure
     * @return UserResponse entity information
     */
    @Transactional
    @PostMapping(value = "/signup")
    @Operation(summary = "Focuses on creating system users")
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.saveUser(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    /**
     * Allow to login into application with username and password
     * Url: /login
     * @param authRequestDTO Authentication request
     * @return Json Response with token and expiration Date
     */
    @PostMapping("/login")
    @Operation(summary = "Allow or Denied User Access")
    public JwtResponseDTO AuthenticationAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername()
                            , authRequestDTO.getPassword()));
            if (authentication.isAuthenticated()) {
                String tk = jwtService.GenerateToken(authRequestDTO.getUsername());
                return JwtResponseDTO.builder()
                        .accessToken(tk)
                        .expiredDate(jwtService.extractExpiration(tk))
                        .build();
            } else {
                throw new UsernameNotFound();
            }
        }catch (AuthenticationException ex){
            throw new InvalidPasswordException("Usuario o Contraseña invalidos");
        }
    }
}
