package com.project.expensetrackingapp.repository.category;

import com.project.expensetrackingapp.repository.entity.category.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepositoryPostgres extends CrudRepository<Category, Long> {
    Category findByName(String name);
}
