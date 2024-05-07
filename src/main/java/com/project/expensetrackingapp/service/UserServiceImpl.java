package com.project.expensetrackingapp.service;

import com.project.expensetrackingapp.repository.entity.UserRequest;
import com.project.expensetrackingapp.repository.entity.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse getUser() {
        return null;
    }

    @Override
    public List<UserResponse> getAllUser() {
        return null;
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public String deleteUser(String username) {
        return null;
    }
}
