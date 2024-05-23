package com.project.expensetrackingapp.repository.user;

import com.project.expensetrackingapp.repository.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * User Mongodb Database Strategy access
 * @author Angel Sic
 */
@Repository
public class UserMongoDatabaseStrategy implements UserDatabaseStrategy {

    @Autowired
    private UserRepositoryMongo repository;

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User save(User entity) {
        if(entity.getId() == 0) {
            entity.setId(Math.abs(System.currentTimeMillis() + UUID.randomUUID().getMostSignificantBits()));
        }
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
