package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.entity.User;
import com.project.expensetrackingapp.repository.entity.UserRequest;
import com.project.expensetrackingapp.repository.entity.UserResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository{
    User save(User user);
    public User findByUsername(String username);
    List<User> findAll();
    //User findFirstById(Long id);
}
