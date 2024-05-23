package com.project.expensetrackingapp.controller;

import com.project.expensetrackingapp.repository.entity.category.Category;
import com.project.expensetrackingapp.service.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Category Controller
 * Base url: /api/v1/categories
 * Define expense or income type data
 * @author Angel Sic
 */
@RestController
@RequestMapping("api/v1/categories")
@Tag(name = "Category CRUD")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * Allow to create new Category data
     * @param category category structure
     * @return New category created
     */
    @Transactional
    @PostMapping
    @Operation(summary = "Create Categories to segment the type of income or expense")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        Category categoryResponse = categoryService.saveCategory(category);
        return ResponseEntity.ok(categoryResponse);
    }

    /**
     * Allows to get all income or expense Categories
     * @return List of Categories
     */
    @GetMapping
    @Operation(summary = "Get all income or expense Categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> category = categoryService.getAllCategories();
        return ResponseEntity.ok(category);
    }

    /**
     * Allows to find category by name
     * Url: /{name}
     * @param name Category name
     * @return Category information
     */
    @GetMapping("/{name}")
    @Operation(summary = "Get income or expense Category by name")
    public ResponseEntity<Category> getCategory(@PathVariable String name){
        Category category = categoryService.getCategory(name);
        return ResponseEntity.ok().body(category);
    }

    /**
     * Allows to Update a Category
     * @param category Category information
     * @return Updated category
     */
    @PatchMapping
    @Operation(summary = "Update income or expense Category")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        Category categoryResponse = categoryService.updateCategory(category);
        return ResponseEntity.ok(categoryResponse);
    }

    /**
     * Allows to Delete a Category by name
     * Url: /{name}
     * @param name Name's Category
     * @return Message indicating the result of the Operation
     */
    @DeleteMapping(value = "/{name}")
    @Operation(summary = "Delete income or expense Category")
    public String deleteCategory(@PathVariable String name){
        return categoryService.deleteCategory(name);
    }
}
