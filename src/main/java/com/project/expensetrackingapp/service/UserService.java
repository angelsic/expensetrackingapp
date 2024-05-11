package com.project.expensetrackingapp.service;

import com.project.expensetrackingapp.repository.entity.UserRequest;
import com.project.expensetrackingapp.repository.entity.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse saveUser(UserRequest userRequest);
    UserResponse getUser(String username);
    List<UserResponse> getAllUser();
    UserResponse updateUser(UserRequest userRequest);
    String deleteUser(String username);
}
