package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.entity.user.User;
import com.project.expensetrackingapp.repository.user.UserMongoDatabaseStrategy;
import com.project.expensetrackingapp.repository.user.UserRepositoryMongo;
import org.junit.Before;
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
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserMongoDatabaseStrategyTest {

    @InjectMocks
    private UserMongoDatabaseStrategy userMongoDatabaseStrategy;

    @Mock
    private UserRepositoryMongo userRepositoryMongo;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByUsername() {
        String username = "testuser";
        User user = new User();
        user.setUsername(username);

        when(userRepositoryMongo.findByUsername(username)).thenReturn(user);

        User result = userMongoDatabaseStrategy.findByUsername(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    @Test
    void testFindAll() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        when(userRepositoryMongo.findAll()).thenReturn(users);

        List<User> result = userMongoDatabaseStrategy.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSave_NewUser() {
        User newUser = new User();
        newUser.setUsername("newuser");

        when(userRepositoryMongo.save(newUser)).thenReturn(newUser);

        User savedUser = userMongoDatabaseStrategy.save(newUser);

        assertNotNull(savedUser);
    }

    @Test
    void testDeleteById() {
        Long userId = 1L;

        userMongoDatabaseStrategy.deleteById(userId);

        verify(userRepositoryMongo, times(1)).deleteById(userId);
    }
}
