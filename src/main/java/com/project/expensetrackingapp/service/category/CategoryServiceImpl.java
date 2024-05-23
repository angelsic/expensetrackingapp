package com.project.expensetrackingapp.service.category;

import com.project.expensetrackingapp.exception.IdNotFound;
import com.project.expensetrackingapp.exception.category.CategoryAlreadyExist;
import com.project.expensetrackingapp.exception.category.CategoryNotExist;
import com.project.expensetrackingapp.exception.category.CategoryNotFound;
import com.project.expensetrackingapp.repository.category.CategoryDatabaseStrategy;
import com.project.expensetrackingapp.repository.entity.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Manage Category CRUD Implementation Data
 * @author Angel Sic
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryDatabaseStrategy categoryDatabaseStrategy;

    @Autowired
    public void setDatabaseStrategy(@Qualifier("categoryStrategy") CategoryDatabaseStrategy categoryDatabaseStrategy){
        this.categoryDatabaseStrategy = categoryDatabaseStrategy;
    }

    @Override
    public Category saveCategory(Category category) {
        if(category.getName() == null){
            throw new CategoryNotFound();
        }
        Category newCategory = categoryDatabaseStrategy.findByName(category.getName());
        if(newCategory == null){
            return categoryDatabaseStrategy.save(category);
        }
        throw new CategoryAlreadyExist(category.getName());
    }

    @Override
    public Category getCategory(String name) {
        return categoryDatabaseStrategy.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDatabaseStrategy.findAll();
    }

    @Override
    public Category updateCategory(Category category) {
        if (category.getId() == 0){
            throw new IdNotFound("Category Id is not defined");
        }else if(category.getName() == null){
            throw new CategoryNotFound();
        }
        Category newCategory = categoryDatabaseStrategy.findById(category.getId()).orElse(null);
        if(newCategory != null){
            Category updCategory = categoryDatabaseStrategy.findByName(category.getName());
            if(updCategory == null) {
                return categoryDatabaseStrategy.save(category);
            }
            throw new CategoryAlreadyExist(category.getName());
        }
        throw new CategoryNotExist(category.getName());
    }

    @Override
    public String deleteCategory(String name) {
        Category category = categoryDatabaseStrategy.findByName(name);
        if(category != null){
            categoryDatabaseStrategy.deleteById(category.getId());
            return name + " was removed";
        }
        return name + " not exist";
    }
}
