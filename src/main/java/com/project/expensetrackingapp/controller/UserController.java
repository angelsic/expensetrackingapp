package com.project.expensetrackingapp.controller;

import com.project.expensetrackingapp.repository.entity.user.UserRequest;
import com.project.expensetrackingapp.repository.entity.user.UserResponse;
import com.project.expensetrackingapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> userResponses = userService.getAllUser();
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getUserProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserResponse userResponse = userService.getUser(username);
        return ResponseEntity.ok().body(userResponse);
    }

    @PatchMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.updateUser(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping(value = "/{username}")
    public String deleteUser(@PathVariable String username){
        return userService.deleteUser(username);
    }

}
