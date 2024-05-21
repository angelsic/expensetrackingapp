package com.project.expensetrackingapp.controller;

import com.project.expensetrackingapp.repository.entity.user.UserRequest;
import com.project.expensetrackingapp.repository.entity.user.UserResponse;
import com.project.expensetrackingapp.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        UserResponse userResponse1 = new UserResponse(1L, "user1", new HashSet<>());
        UserResponse userResponse2 = new UserResponse(2L, "user2", new HashSet<>());
        List<UserResponse> userResponses = Arrays.asList(userResponse1, userResponse2);
        Mockito.when(userService.getAllUser()).thenReturn(userResponses);

        ResponseEntity<List<UserResponse>> response = userController.getAllUsers();

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(userResponses);
    }

    @Test
    void testGetUserProfile() {
        String username = "testUser";
        UserResponse userResponse = new UserResponse(1L, username, new HashSet<>());
        Mockito.when(userService.getUser(username)).thenReturn(userResponse);

        // Simular el contexto de seguridad
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn(username);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        ResponseEntity<UserResponse> response = userController.getUserProfile();

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(userResponse);
    }

    @Test
    void testUpdateUser() {
        UserRequest userRequest = new UserRequest(1L, "testUser", "password", new HashSet<>());
        UserResponse userResponse = new UserResponse(1L, "testUser", new HashSet<>());
        Mockito.when(userService.updateUser(userRequest)).thenReturn(userResponse);

        ResponseEntity<UserResponse> response = userController.updateUser(userRequest);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(userResponse);
    }

    @Test
    void testDeleteUser() {
        String username = "testUser";
        Mockito.when(userService.deleteUser(username)).thenReturn("User deleted successfully");

        String response = userController.deleteUser(username);

        assertThat(response).isEqualTo("User deleted successfully");
    }
}
