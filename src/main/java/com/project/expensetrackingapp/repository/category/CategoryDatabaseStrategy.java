package com.project.expensetrackingapp.repository.category;

import com.project.expensetrackingapp.repository.entity.category.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDatabaseStrategy {
    Category findByName(String name);
    List<Category> findAll();
    Category save(Category entity);
    void deleteById(Long id);
    Optional<Category> findById(Long id);
}
