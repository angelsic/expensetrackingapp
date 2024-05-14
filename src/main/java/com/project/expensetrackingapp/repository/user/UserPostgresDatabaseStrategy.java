package com.project.expensetrackingapp.repository.user;

import com.project.expensetrackingapp.repository.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserPostgresDatabaseStrategy implements UserDatabaseStrategy {

    @Autowired
    private UserRepositoryPostgres repository;

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Transactional
    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
