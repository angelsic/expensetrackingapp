package com.project.expensetrackingapp.service;

import com.project.expensetrackingapp.exception.user.PasswordNotFound;
import com.project.expensetrackingapp.exception.user.UserAlreadyExist;
import com.project.expensetrackingapp.exception.user.UserNotExist;
import com.project.expensetrackingapp.exception.user.UsernameNotFound;
import com.project.expensetrackingapp.repository.entity.role.UserRole;
import com.project.expensetrackingapp.repository.entity.user.User;
import com.project.expensetrackingapp.repository.entity.user.UserRequest;
import com.project.expensetrackingapp.repository.entity.user.UserResponse;
import com.project.expensetrackingapp.repository.role.UserRoleDatabaseStrategy;
import com.project.expensetrackingapp.repository.user.UserDatabaseStrategy;
import com.project.expensetrackingapp.service.user.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDatabaseStrategy userDatabaseStrategy;

    @Mock
    private UserRoleDatabaseStrategy userRoleDatabaseStrategy;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        userService.setDatabaseStrategy(userDatabaseStrategy);
        userService.setRolDatabaseStrategy(userRoleDatabaseStrategy);
    }

    @Test
    void testSaveUser_UserAlreadyExist() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("john.doe");
        userRequest.setPassword("password123");

        User existingUser = new User();
        existingUser.setUsername("john.doe");

        when(userDatabaseStrategy.findByUsername(userRequest.getUsername())).thenReturn(existingUser);

        try {
            userService.saveUser(userRequest);
            fail("UserAlreadyExist exception not thrown");
        } catch (UserAlreadyExist e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testSaveUser_SuccessfulSave() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("john.doe");
        userRequest.setPassword("password123");
        Set<UserRole> roles = new HashSet<>();
        UserRole role1 = new UserRole();
        role1.setName("ROLE_USER");
        roles.add(role1);
        userRequest.setRoles(roles);

        User user = new User();
        user.setUsername("john.doe");
        user.setPassword("encodedPassword");
        user.setRoles(roles);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("john.doe");
        savedUser.setPassword("encodedPassword");
        savedUser.setRoles(roles);

        when(userDatabaseStrategy.findByUsername(userRequest.getUsername())).thenReturn(null);
        when(userRoleDatabaseStrategy.findAll()).thenReturn(new ArrayList<>(roles));
        when(userRoleDatabaseStrategy.save(any(UserRole.class))).thenAnswer(i -> i.getArguments()[0]);
        when(userDatabaseStrategy.save(any(User.class))).thenReturn(savedUser);
        when(modelMapper.map(userRequest, User.class)).thenReturn(user);
        when(modelMapper.map(savedUser, UserResponse.class)).thenReturn(new UserResponse());

        UserResponse userResponse = userService.saveUser(userRequest);

        assertNotNull(userResponse);
    }

    @Test
    void testGetUser() {
        String username = "john.doe";
        User user = new User();
        user.setUsername(username);

        when(userDatabaseStrategy.findByUsername(username)).thenReturn(user);
        when(modelMapper.map(user, UserResponse.class)).thenReturn(new UserResponse());

        UserResponse userResponse = userService.getUser(username);

        assertNotNull(userResponse);
    }

    @Test
    void testGetAllUser() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("john.doe");
        User user2 = new User();
        user2.setUsername("jane.doe");
        users.add(user1);
        users.add(user2);

        Type setOfDTOsType = new TypeToken<List<UserResponse>>() {}.getType();

        when(userDatabaseStrategy.findAll()).thenReturn(users);
        when(modelMapper.map(users, setOfDTOsType)).thenReturn(new ArrayList<>());

        List<UserResponse> userResponses = userService.getAllUser();

        assertNotNull(userResponses);
    }

    @Test
    void testSaveUser_UsernameNotFound() {
        UserRequest userRequest = new UserRequest();
        userRequest.setPassword("password123");

        try {
            userService.saveUser(userRequest);
            fail("UsernameNotFound exception not thrown");
        } catch (UsernameNotFound e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testSaveUser_PasswordNotFound() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("john.doe");

        try {
            userService.saveUser(userRequest);
            fail("PasswordNotFound exception not thrown");
        } catch (PasswordNotFound e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testUpdateUser_UsernameNotFound() {
        UserRequest userRequest = new UserRequest();
        userRequest.setPassword("password123");

        try {
            userService.updateUser(userRequest);
            fail("UsernameNotFound exception not thrown");
        } catch (UsernameNotFound e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testUpdateUser_PasswordNotFound() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("john.doe");

        try {
            userService.updateUser(userRequest);
            fail("PasswordNotFound exception not thrown");
        } catch (PasswordNotFound e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testUpdateUser_UserNotExist() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("john.doe");
        userRequest.setPassword("password123");

        when(userDatabaseStrategy.findByUsername(userRequest.getUsername())).thenReturn(null);

        try {
            userService.updateUser(userRequest);
            fail("UserNotExist exception not thrown");
        } catch (UserNotExist e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testUpdateUser_SuccessfulUpdate() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("john.doe");
        userRequest.setPassword("password123");
        Set<UserRole> roles = new HashSet<>();
        UserRole role1 = new UserRole();
        role1.setName("ROLE_USER");
        roles.add(role1);
        userRequest.setRoles(roles);

        User updatedUser = new User();
        updatedUser.setUsername("john.doe");
        updatedUser.setPassword("encodedPassword");
        updatedUser.setRoles(roles);

        when(userDatabaseStrategy.findByUsername(userRequest.getUsername())).thenReturn(updatedUser);
        when(userRoleDatabaseStrategy.findAll()).thenReturn(new ArrayList<>(roles));
        when(userRoleDatabaseStrategy.save(any(UserRole.class))).thenAnswer(i -> i.getArguments()[0]);
        when(userDatabaseStrategy.save(any(User.class))).thenReturn(updatedUser);
        when(modelMapper.map(userRequest, User.class)).thenReturn(updatedUser);
        when(modelMapper.map(updatedUser, UserResponse.class)).thenReturn(new UserResponse());

        UserResponse userResponse = userService.updateUser(userRequest);

        assertNotNull(userResponse);
    }

    @Test
    void testDeleteUser_UserExists() {
        String username = "john.doe";
        User user = new User();
        user.setId(1L);
        user.setUsername(username);

        when(userDatabaseStrategy.findByUsername(username)).thenReturn(user);

        String result = userService.deleteUser(username);

        assertEquals(username + " was removed", result);
    }

    @Test
    void testDeleteUser_UserNotExists() {
        String username = "nonexistentuser";

        when(userDatabaseStrategy.findByUsername(username)).thenReturn(null);

        String result = userService.deleteUser(username);

        assertEquals(username + " not exist", result);
    }
}
