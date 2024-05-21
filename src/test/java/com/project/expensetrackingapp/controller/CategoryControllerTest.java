package com.project.expensetrackingapp.controller;

import com.project.expensetrackingapp.repository.entity.category.Category;
import com.project.expensetrackingapp.service.category.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategory() {
        Category category = new Category(1L, "Test Category");
        Mockito.when(categoryService.saveCategory(category)).thenReturn(category);

        ResponseEntity<Category> response = categoryController.saveCategory(category);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(category);
    }

    @Test
    void testGetAllCategories() {
        Category category1 = new Category(1L, "Category 1");
        Category category2 = new Category(2L, "Category 2");
        List<Category> categories = Arrays.asList(category1, category2);
        Mockito.when(categoryService.getAllCategories()).thenReturn(categories);

        ResponseEntity<List<Category>> response = categoryController.getAllCategories();

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(categories);
    }

    @Test
    void testGetCategory() {
        Category category = new Category(1L, "Test Category");
        Mockito.when(categoryService.getCategory("Test Category")).thenReturn(category);

        ResponseEntity<Category> response = categoryController.getCategory("Test Category");

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(category);
    }

    @Test
    void testUpdateCategory() {
        Category category = new Category(1L, "Test Category");
        Mockito.when(categoryService.updateCategory(category)).thenReturn(category);

        ResponseEntity<Category> response = categoryController.updateCategory(category);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(category);
    }

    @Test
    void testDeleteCategory() {
        String categoryName = "Test Category";
        Mockito.when(categoryService.deleteCategory(categoryName)).thenReturn("Category deleted successfully");

        String response = categoryController.deleteCategory(categoryName);

        assertThat(response).isEqualTo("Category deleted successfully");
    }
}
