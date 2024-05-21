package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.category.CategoryMongoDatabaseStrategy;
import com.project.expensetrackingapp.repository.category.CategoryRepositoryMongo;
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
class CategoryMongoDatabaseStrategyTest {

    @InjectMocks
    private CategoryMongoDatabaseStrategy categoryMongoDatabaseStrategy;

    @Mock
    private CategoryRepositoryMongo categoryRepositoryMongo;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByName() {
        String categoryName = "Test Category";
        Category category = new Category();
        category.setName(categoryName);

        when(categoryRepositoryMongo.findByName(categoryName)).thenReturn(category);

        Category result = categoryMongoDatabaseStrategy.findByName(categoryName);

        assertNotNull(result);
        assertEquals(categoryName, result.getName());
    }

    @Test
    void testFindAll() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        categories.add(new Category());

        when(categoryRepositoryMongo.findAll()).thenReturn(categories);

        List<Category> result = categoryMongoDatabaseStrategy.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSave() {
        Category category = new Category();
        category.setId(0L);
        category.setName("New Category");

        when(categoryRepositoryMongo.save(category)).thenReturn(category);

        Category savedCategory = categoryMongoDatabaseStrategy.save(category);

        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getId());
    }

    @Test
    void testDeleteById() {
        Long categoryId = 1L;

        categoryMongoDatabaseStrategy.deleteById(categoryId);

        verify(categoryRepositoryMongo, times(1)).deleteById(categoryId);
    }

    @Test
    void testFindById() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);

        when(categoryRepositoryMongo.findById(categoryId)).thenReturn(Optional.of(category));

        Optional<Category> result = categoryMongoDatabaseStrategy.findById(categoryId);

        assertNotNull(result);
        assertEquals(Optional.of(categoryId), Optional.of(result.get().getId()));
    }
}
