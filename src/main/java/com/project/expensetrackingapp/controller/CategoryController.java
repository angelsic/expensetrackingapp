package com.project.expensetrackingapp.controller;

import com.project.expensetrackingapp.repository.entity.category.Category;
import com.project.expensetrackingapp.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Transactional
    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        Category categoryResponse = categoryService.saveCategory(category);
        return ResponseEntity.ok(categoryResponse);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> category = categoryService.getAllCategories();
        return ResponseEntity.ok(category);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Category> getCategory(@PathVariable String name){
        Category category = categoryService.getCategory(name);
        return ResponseEntity.ok().body(category);
    }

    @PatchMapping
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        Category categoryResponse = categoryService.updateCategory(category);
        return ResponseEntity.ok(categoryResponse);
    }

    @DeleteMapping(value = "/{name}")
    public String deleteCategory(@PathVariable String name){
        return categoryService.deleteCategory(name);
    }
}
