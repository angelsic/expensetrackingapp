package com.project.expensetrackingapp.service.category;

import com.project.expensetrackingapp.repository.entity.category.Category;

import java.util.List;

/**
 * Category Service Interface
 * @author Angel Sic
 */
public interface CategoryService {
    Category saveCategory(Category category);
    Category getCategory(String name);
    List<Category> getAllCategories();
    Category updateCategory(Category category);
    String deleteCategory(String name);
}
