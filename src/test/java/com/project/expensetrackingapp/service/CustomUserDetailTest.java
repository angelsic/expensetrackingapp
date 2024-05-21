package com.project.expensetrackingapp.service;

import com.project.expensetrackingapp.repository.entity.role.UserRole;
import com.project.expensetrackingapp.repository.entity.user.User;
import com.project.expensetrackingapp.service.user.CustomUserDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CustomUserDetailTest {

    @Mock
    private User user;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAuthorities() {
        Set<UserRole> roles = new HashSet<>();
        UserRole role1 = new UserRole();
        role1.setName("ROLE_USER");
        UserRole role2 = new UserRole();
        role2.setName("ROLE_ADMIN");
        roles.add(role1);
        roles.add(role2);

        when(user.getRoles()).thenReturn(roles);

        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        assertNotNull(customUserDetail.getAuthorities());
        assertEquals(2, customUserDetail.getAuthorities().size());
    }

    @Test
    void testGetPassword() {
        String password = "password123";
        when(user.getPassword()).thenReturn(password);

        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        assertEquals(password, customUserDetail.getPassword());
    }

    @Test
    void testGetUsername() {
        String username = "john.doe";
        when(user.getUsername()).thenReturn(username);

        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        assertEquals(username, customUserDetail.getUsername());
    }

    @Test
    public void testIsAccountNonExpired() {
        String username = "john.doe";
        String password = "password123";
        Set<UserRole> roles = new HashSet<>();
        UserRole role1 = new UserRole();
        role1.setName("ROLE_USER");
        roles.add(role1);

        when(user.getUsername()).thenReturn(username);
        when(user.getPassword()).thenReturn(password);
        when(user.getRoles()).thenReturn(roles);

        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        assertTrue(customUserDetail.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        assertTrue(customUserDetail.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        assertTrue(customUserDetail.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        assertTrue(customUserDetail.isEnabled());
    }
}
