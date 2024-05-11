package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class MongoDatabaseStrategy implements DatabaseStrategy {

    @Autowired
    private RepositoryMongo repositoryMongo;

    @Override
    public User findByUsername(String username) {
        return repositoryMongo.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return repositoryMongo.findAll();
    }

    @Override
    public User save(User entity) {
        if(entity.getId() == 0) {
            entity.setId(Math.abs(System.currentTimeMillis() + UUID.randomUUID().getMostSignificantBits()));
        }
        return repositoryMongo.save(entity);
    }

    @Override
    public void deleteById(Long id){
        repositoryMongo.deleteById(id);
    }
}
