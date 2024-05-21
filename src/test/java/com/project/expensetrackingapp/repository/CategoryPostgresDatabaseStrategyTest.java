package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.category.CategoryPostgresDatabaseStrategy;
import com.project.expensetrackingapp.repository.category.CategoryRepositoryPostgres;
import com.project.expensetrackingapp.repository.entity.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class CategoryPostgresDatabaseStrategyTest {

    @InjectMocks
    private CategoryPostgresDatabaseStrategy categoryPostgresDatabaseStrategy;

    @Mock
    private CategoryRepositoryPostgres categoryRepositoryPostgres;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByName() {
        String categoryName = "Test Category";
        Category category = new Category();
        category.setName(categoryName);

        when(categoryRepositoryPostgres.findByName(categoryName)).thenReturn(category);

        Category result = categoryPostgresDatabaseStrategy.findByName(categoryName);

        assertNotNull(result);
        assertEquals(categoryName, result.getName());
    }

    @Test
    void testFindAll() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        categories.add(new Category());

        when(categoryRepositoryPostgres.findAll()).thenReturn(categories);

        List<Category> result = categoryPostgresDatabaseStrategy.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSave() {
        Category category = new Category();
        category.setName("New Category");

        when(categoryRepositoryPostgres.save(category)).thenReturn(category);

        Category savedCategory = categoryPostgresDatabaseStrategy.save(category);

        assertNotNull(savedCategory);
    }

    @Test
    void testDeleteById() {
        Long categoryId = 1L;

        categoryPostgresDatabaseStrategy.deleteById(categoryId);

        verify(categoryRepositoryPostgres, times(1)).deleteById(categoryId);
    }

    @Test
    void testFindById() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);

        when(categoryRepositoryPostgres.findById(categoryId)).thenReturn(Optional.of(category));

        Optional<Category> result = categoryPostgresDatabaseStrategy.findById(categoryId);

        assertNotNull(result);
        assertEquals(Optional.of(categoryId), Optional.of(result.get().getId()));
    }
}
