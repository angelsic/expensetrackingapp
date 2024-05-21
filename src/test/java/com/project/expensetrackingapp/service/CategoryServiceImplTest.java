package com.project.expensetrackingapp.service;

import com.project.expensetrackingapp.exception.IdNotFound;
import com.project.expensetrackingapp.exception.category.CategoryAlreadyExist;
import com.project.expensetrackingapp.exception.category.CategoryNotExist;
import com.project.expensetrackingapp.exception.category.CategoryNotFound;
import com.project.expensetrackingapp.repository.category.CategoryDatabaseStrategy;
import com.project.expensetrackingapp.repository.entity.category.Category;
import com.project.expensetrackingapp.service.category.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @Mock
    private CategoryDatabaseStrategy categoryDatabaseStrategy;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategory_Success() {
        Category category = new Category(1L, "Test Category");
        when(categoryDatabaseStrategy.findByName("Test Category")).thenReturn(null);
        when(categoryDatabaseStrategy.save(category)).thenReturn(category);

        Category savedCategory = categoryService.saveCategory(category);

        assertNotNull(savedCategory);
        assertEquals("Test Category", savedCategory.getName());
    }

    @Test
    void testSaveCategory_CategoryAlreadyExist() {
        Category category = new Category(1L, "Existing Category");
        when(categoryDatabaseStrategy.findByName("Existing Category")).thenReturn(category);

        assertThrows(CategoryAlreadyExist.class, () -> categoryService.saveCategory(category));
    }

    @Test
    void testGetCategory() {
        Category category = new Category(1L, "Test Category");
        when(categoryDatabaseStrategy.findByName("Test Category")).thenReturn(category);

        Category retrievedCategory = categoryService.getCategory("Test Category");

        assertNotNull(retrievedCategory);
        assertEquals("Test Category", retrievedCategory.getName());
    }

    @Test
    void testGetAllCategories() {
        when(categoryDatabaseStrategy.findAll()).thenReturn(Collections.emptyList());

        List<Category> categories = categoryService.getAllCategories();

        assertNotNull(categories);
        assertTrue(categories.isEmpty());
    }

    @Test
    void testUpdateCategory_Success() {
        Category category = new Category(1L, "Test Category");
        when(categoryDatabaseStrategy.findById(1L)).thenReturn(Optional.of(category));
        when(categoryDatabaseStrategy.findByName("Test Category")).thenReturn(null);
        when(categoryDatabaseStrategy.save(category)).thenReturn(category);

        Category updatedCategory = categoryService.updateCategory(category);

        assertNotNull(updatedCategory);
        assertEquals("Test Category", updatedCategory.getName());
    }

    @Test
    void testUpdateCategory_IdNotFound() {
        Category category = new Category(0L, "Test Category");

        assertThrows(IdNotFound.class, () -> categoryService.updateCategory(category));
    }

    @Test
    void testUpdateCategory_CategoryNotFound() {
        Category category = new Category(1L, null);

        assertThrows(CategoryNotFound.class, () -> categoryService.updateCategory(category));
    }

    @Test
    void testUpdateCategory_CategoryAlreadyExist() {
        Category category = new Category(1L, "Existing Category");
        when(categoryDatabaseStrategy.findById(1L)).thenReturn(Optional.of(category));
        when(categoryDatabaseStrategy.findByName("Existing Category")).thenReturn(category);

        assertThrows(CategoryAlreadyExist.class, () -> categoryService.updateCategory(category));
    }

    @Test
    void testUpdateCategory_CategoryNotExist() {
        Category category = new Category(1L, "Nonexistent Category");
        when(categoryDatabaseStrategy.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotExist.class, () -> categoryService.updateCategory(category));
    }

    @Test
    void testDeleteCategory_Success() {
        Category category = new Category(1L, "Test Category");
        when(categoryDatabaseStrategy.findByName("Test Category")).thenReturn(category);
        doNothing().when(categoryDatabaseStrategy).deleteById(1L);

        String result = categoryService.deleteCategory("Test Category");

        assertEquals("Test Category was removed", result);
    }

    @Test
    void testDeleteCategory_NotExist() {
        when(categoryDatabaseStrategy.findByName("Nonexistent Category")).thenReturn(null);

        String result = categoryService.deleteCategory("Nonexistent Category");

        assertEquals("Nonexistent Category not exist", result);
    }
}