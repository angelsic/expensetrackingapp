package com.project.expensetrackingapp.controller;

import com.project.expensetrackingapp.repository.entity.user.UserRequest;
import com.project.expensetrackingapp.repository.entity.user.UserResponse;
import com.project.expensetrackingapp.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User Controller
 * Base url: /api/v1/users
 * Manage user information
 * @author Angel Sic
 */
@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users CRUD")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * Allows to Get all users only for ROLE_ADMIN
     * @return List of Users
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    @Operation(summary = "Obtain the information of all users of the system. Limited use for administrator users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> userResponses = userService.getAllUser();
        return ResponseEntity.ok(userResponses);
    }

    /**
     * Allows to obtain information from the logged in user
     * Url: /profile
     * @return Logged in User
     */
    @GetMapping("/profile")
    @Operation(summary = "Get logged User information")
    public ResponseEntity<UserResponse> getUserProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserResponse userResponse = userService.getUser(username);
        return ResponseEntity.ok().body(userResponse);
    }

    /**
     * Allows to Update User information
     * @param userRequest User Information
     * @return Updated User
     */
    @PatchMapping
    @Operation(summary = "Update User information")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.updateUser(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    /**
     * Allows to Delete Users information by username
     * Url: /{username}
     * @param username User name information
     * @return Message indicating the result of the Operation
     */
    @DeleteMapping(value = "/{username}")
    @Operation(summary = "Delete User information by Username")
    public String deleteUser(@PathVariable String username){
        return userService.deleteUser(username);
    }

}
