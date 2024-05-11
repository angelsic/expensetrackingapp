package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PostgresDatabaseStrategy implements DatabaseStrategy{

    @Autowired
    private RepositoryPostgres repositoryPostgres;

    @Override
    public User findByUsername(String username) {
        return repositoryPostgres.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) repositoryPostgres.findAll();
    }

    @Transactional
    @Override
    public User save(User entity) {
        return repositoryPostgres.save(entity);
    }

    @Override
    public void deleteById(Long id){
        repositoryPostgres.deleteById(id);
    }
}
