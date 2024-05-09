package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositoryMongo extends MongoRepository<User, Long> {
    public User findByUsername(String username);
}
