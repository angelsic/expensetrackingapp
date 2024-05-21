package com.project.expensetrackingapp.repository.entity;

import com.project.expensetrackingapp.repository.entity.category.Category;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest {

    @Test
    void testBuilder() {
        Category category = Category.builder()
                .id(1L)
                .name("Test Category")
                .build();

        assertThat(category.getId()).isEqualTo(1L);
        assertThat(category.getName()).isEqualTo("Test Category");
    }

    @Test
    void testGettersAndSetters() {
        Category category = new Category();

        category.setId(1L);
        category.setName("Test Category");

        assertThat(category.getId()).isEqualTo(1L);
        assertThat(category.getName()).isEqualTo("Test Category");
    }

    @Test
    void testToString() {
        Category category = Category.builder()
                .id(1L)
                .name("Test Category")
                .build();

        String expectedToString = "Category(id=1, name=Test Category)";
        assertThat(category.toString()).isEqualTo(expectedToString);
    }

    @Test
    void testEqualsWithSameObject() {
        Category category = Category.builder()
                .id(1L)
                .name("Test Category")
                .build();

        assertThat(category).isEqualTo(category);
    }

    @Test
    void testEqualsWithDifferentType() {
        Category category = Category.builder()
                .id(1L)
                .name("Test Category")
                .build();

        assertThat(category).isNotEqualTo(new Object());
    }

    @Test
    void testEqualsWithNull() {
        Category category = Category.builder()
                .id(1L)
                .name("Test Category")
                .build();

        assertThat(category).isNotEqualTo(null);
    }

    @Test
    void testEqualsWithDifferentValues() {
        Category category1 = Category.builder()
                .id(1L)
                .name("Test Category 1")
                .build();

        Category category2 = Category.builder()
                .id(2L)
                .name("Test Category 2")
                .build();

        assertThat(category1).isNotEqualTo(category2);
    }

    @Test
    void testHashCodeForEqualObjects() {
        Category category1 = Category.builder()
                .id(1L)
                .name("Test Category")
                .build();

        Category category2 = Category.builder()
                .id(1L)
                .name("Test Category")
                .build();

        assertThat(category1.hashCode()).isEqualTo(category2.hashCode());
    }

    @Test
    void testHashCodeForDifferentObjects() {
        Category category1 = Category.builder()
                .id(1L)
                .name("Test Category 1")
                .build();

        Category category2 = Category.builder()
                .id(2L)
                .name("Test Category 2")
                .build();

        assertThat(category1.hashCode()).isNotEqualTo(category2.hashCode());
    }

    @Test
    void testHashCodeForNull() {
        Category category = Category.builder()
                .id(1L)
                .name("Test Category")
                .build();

        assertThat(category.hashCode()).isNotEqualTo(null);
    }
}