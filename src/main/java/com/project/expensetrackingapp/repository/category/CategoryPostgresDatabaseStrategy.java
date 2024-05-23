package com.project.expensetrackingapp.repository.category;

import com.project.expensetrackingapp.repository.entity.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Category Postgres Database Strategy access
 * @author Angel Sic
 */
@Repository
public class CategoryPostgresDatabaseStrategy implements CategoryDatabaseStrategy{
    @Autowired
    private CategoryRepositoryPostgres repository;

    @Override
    public Category findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Category> findAll() {
        return (List<Category>) repository.findAll();
    }

    @Override
    public Category save(Category entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }
}
