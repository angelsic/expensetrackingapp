package com.project.expensetrackingapp.service.user;

import com.project.expensetrackingapp.repository.entity.user.UserRequest;
import com.project.expensetrackingapp.repository.entity.user.UserResponse;

import java.util.List;

/**
 * User Service Interface
 * @author Angel Sic
 */
public interface UserService {
    UserResponse saveUser(UserRequest userRequest);
    UserResponse getUser(String username);
    List<UserResponse> getAllUser();
    UserResponse updateUser(UserRequest userRequest);
    String deleteUser(String username);
}
