package com.project.expensetrackingapp.service;

import com.project.expensetrackingapp.exception.user.UsernameNotFound;
import com.project.expensetrackingapp.repository.entity.user.User;
import com.project.expensetrackingapp.repository.user.UserDatabaseStrategy;
import com.project.expensetrackingapp.service.user.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserDatabaseStrategy userDatabaseStrategy;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        userDetailsService.setDatabaseStrategy(userDatabaseStrategy);
    }

    @Test
    void testLoadUserByUsername_UserFound() {
        String username = "john.doe";
        User user = new User();
        user.setUsername(username);

        when(userDatabaseStrategy.findByUsername(username)).thenReturn(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        String username = "nonexistentuser";

        when(userDatabaseStrategy.findByUsername(username)).thenReturn(null);

        try {
            userDetailsService.loadUserByUsername(username);
            fail("UsernameNotFound exception not thrown");
        } catch (UsernameNotFound e) {
            assertNotNull(e.getMessage());
        }
    }
}
