package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.entity.role.UserRole;
import com.project.expensetrackingapp.repository.role.UserRolePostgresDatabaseStrategy;
import com.project.expensetrackingapp.repository.role.UserRoleRepositoryPostgres;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserRolePostgresDatabaseStrategyTest {

    @InjectMocks
    private UserRolePostgresDatabaseStrategy userRolePostgresDatabaseStrategy;

    @Mock
    private UserRoleRepositoryPostgres userRoleRepositoryPostgres;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(new UserRole(1L,"ROLE_ADMIN"));
        userRoles.add(new UserRole(2L, "ROLE_USER"));

        when(userRoleRepositoryPostgres.findAll()).thenReturn(userRoles);

        List<UserRole> result = userRolePostgresDatabaseStrategy.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSave_NewRole() {
        UserRole newRole = new UserRole(1L,"ROLE_MANAGER");

        when(userRoleRepositoryPostgres.findAll()).thenReturn(new ArrayList<>());
        when(userRoleRepositoryPostgres.save(newRole)).thenReturn(newRole);

        UserRole savedRole = userRolePostgresDatabaseStrategy.save(newRole);

        assertNotNull(savedRole);
        assertEquals("ROLE_MANAGER", savedRole.getName());
    }

    @Test
    void testSave_ExistingRole() {
        UserRole existingRole = new UserRole(1L,"ROLE_USER");

        when(userRoleRepositoryPostgres.findAll()).thenReturn(List.of(existingRole));

        UserRole savedRole = userRolePostgresDatabaseStrategy.save(existingRole);

        assertNotNull(savedRole);
        assertEquals("ROLE_USER", savedRole.getName());
    }

    @Test
    void testFindByName() {
        String roleName = "ROLE_ADMIN";
        UserRole role = new UserRole(1L,roleName);

        when(userRoleRepositoryPostgres.findAll()).thenReturn(List.of(role));

        Optional<UserRole> result = userRolePostgresDatabaseStrategy.findByName(roleName);

        assertNotNull(result);
        assertEquals(roleName, result.get().getName());
    }
}
