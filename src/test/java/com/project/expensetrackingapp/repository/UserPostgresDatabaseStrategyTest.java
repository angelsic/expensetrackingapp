package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.entity.user.User;
import com.project.expensetrackingapp.repository.user.UserPostgresDatabaseStrategy;
import com.project.expensetrackingapp.repository.user.UserRepositoryPostgres;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserPostgresDatabaseStrategyTest {

    @InjectMocks
    private UserPostgresDatabaseStrategy userPostgresDatabaseStrategy;

    @Mock
    private UserRepositoryPostgres userRepositoryPostgres;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByUsername() {
        String username = "testuser";
        User user = new User();
        user.setUsername(username);

        when(userRepositoryPostgres.findByUsername(username)).thenReturn(user);

        User result = userPostgresDatabaseStrategy.findByUsername(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    @Test
    void testFindAll() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        when(userRepositoryPostgres.findAll()).thenReturn(users);

        List<User> result = userPostgresDatabaseStrategy.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSave() {
        User newUser = new User();
        newUser.setUsername("newuser");

        when(userRepositoryPostgres.save(newUser)).thenReturn(newUser);

        User savedUser = userPostgresDatabaseStrategy.save(newUser);

        assertNotNull(savedUser);
    }

    @Test
    void testDeleteById() {
        Long userId = 1L;

        userPostgresDatabaseStrategy.deleteById(userId);

        verify(userRepositoryPostgres, times(1)).deleteById(userId);
    }
}
