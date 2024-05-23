package com.project.expensetrackingapp.repository.user;

import com.project.expensetrackingapp.repository.entity.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * User Mongodb Connection Interface
 * @author Angel Sic
 */
@Repository
public interface UserRepositoryMongo extends MongoRepository<User, Long> {
    User findByUsername(String username);
}
