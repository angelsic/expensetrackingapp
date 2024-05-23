package com.project.expensetrackingapp.repository.category;

import com.project.expensetrackingapp.repository.entity.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Category Mongodb Connection Interface
 * @author Angel Sic
 */
@Repository
public interface CategoryRepositoryMongo extends MongoRepository<Category, Long> {
    Category findByName(String name);
}
